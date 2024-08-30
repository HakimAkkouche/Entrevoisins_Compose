package com.haksoftware.entrevoisins_kotlin_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.haksoftware.entrevoisins_kotlin_compose.ui.AddNeighbourScreen
import com.haksoftware.entrevoisins_kotlin_compose.ui.ListNeighbourActivityScreen
import com.haksoftware.entrevoisins_kotlin_compose.ui.NeighbourDetailsScreen
import com.haksoftware.entrevoisins_kotlin_compose.ui.theme.Entrevoisins_Kotlin_ComposeTheme
import com.haksoftware.entrevoisins_kotlin_compose.viewmodel.NeighboursViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            Entrevoisins_Kotlin_ComposeTheme {
                Scaffold(
                    topBar = {MyAppTopAppBar()},
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                ) { innerPadding ->
                    NavigationHost(
                        navController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyAppTopAppBar() {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary, // Use primary color from theme
                titleContentColor = MaterialTheme.colorScheme.onPrimary // Use onPrimary color from theme
            )
        )
    }
    @Composable
    private fun NavigationHost(
        navController: NavHostController,
        innerPadding: PaddingValues
    ) {
        NavHost(
            navController = navController,
            startDestination = "neighbours_list_screen",
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(route = "neighbours_list_screen") {
                ListNeighbourActivityScreen(navController = navController)
            }
            composable(
                route = "neighbour_details_screen/{neighbourId}",
                arguments = listOf(navArgument("neighbourId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val neighbourId: Int =
                    backStackEntry.arguments?.getInt("neighbourId") ?: -1
                NeighbourDetailsScreen(
                    neighbourId = neighbourId,
                    onBackClicked = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = "add_neighbour", // Simplification de la route
            ) {
                AddNeighbourScreen(
                    onBackClicked = { navController.navigateUp() }
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Entrevoisins_Kotlin_ComposeTheme {
            NavigationHost(
                navController = rememberNavController(),
                innerPadding = PaddingValues()
            )
        }
    }
}
