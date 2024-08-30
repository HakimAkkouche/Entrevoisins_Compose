package com.haksoftware.entrevoisins_kotlin_compose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.haksoftware.entrevoisins_kotlin_compose.ui.theme.pink
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun ListNeighbourActivityScreen(
    navController: NavHostController
) {
    val tabs = listOf(
        TabItem.MyNeighbours,
        TabItem.MyFavorites
    )

    val pagerState = rememberPagerState(pageCount = {tabs.size})

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .imePadding()
    ) {
        Column {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState, navController = navController) // Pass the NavController
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState, navController: NavHostController) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen(navController)
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    SecondaryTabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = Color.White,
        contentColor = pink,
        indicator = {
            SecondaryIndicator(
                Modifier. tabIndicatorOffset(pagerState.currentPage,
                    matchContentSize = false)
            )
        }
        ) {
        tabs.forEachIndexed{index, tabItem ->
            LeadingIconTab(
                selected =  pagerState.currentPage == index ,
                text = { Text(text = stringResource(tabItem.title).uppercase(),
                    style = MaterialTheme.typography.titleMedium) },
                icon = { Icon(painter = painterResource(id = tabItem.icon), contentDescription = stringResource(
                    id = tabItem.title))},
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}
