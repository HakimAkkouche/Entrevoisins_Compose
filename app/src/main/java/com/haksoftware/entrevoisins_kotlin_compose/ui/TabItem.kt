package com.haksoftware.entrevoisins_kotlin_compose.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.haksoftware.entrevoisins_kotlin_compose.viewmodel.NeighboursViewModel
import com.haksoftware.entrevoisins_kotlin_compose.R

sealed class TabItem(
    var icon: Int,
    var title: Int,
    var screen: @Composable (NavHostController) -> Unit
) {
    object MyNeighbours : TabItem(
        R.drawable.baseline_home_24,
        R.string.my_neighbours,
        { navController -> NeighboursListComposable(navController, isFavoritePage = false) }
    )

    object MyFavorites : TabItem(
        R.drawable.ic_star_white_24dp,
        R.string.my_favorites,
        { navController -> NeighboursListComposable(navController, isFavoritePage = true) }
    )
}

@Composable
fun NeighboursListComposable(navController: NavHostController, isFavoritePage: Boolean) {
    val viewModel: NeighboursViewModel = hiltViewModel()
    val context = LocalContext.current

    NeighboursListScreen(
        onSelectedNeighbour = { neighbourId ->
            navController.navigate("neighbour_details_screen/$neighbourId")
        },
        onDeleteClicked = { neighbourId ->
            viewModel.deleteNeighbour(neighbourId) { success ->
                if (success) {
                    Toast.makeText(context,
                        context.getString(R.string.neighbour_deleted_successfully), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context,
                        context.getString(R.string.failed_to_delete_neighbour), Toast.LENGTH_SHORT).show()
                }
            }
        },
        onFabClicked = {
            navController.navigate("add_neighbour")
        },
        viewModel = viewModel,
        isFavoritePage = isFavoritePage
    )
}
