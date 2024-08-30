package com.haksoftware.entrevoisins_kotlin_compose.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.haksoftware.entrevoisins_kotlin_compose.viewmodel.NeighboursViewModel
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haksoftware.entrevoisins_kotlin_compose.R
import com.haksoftware.entrevoisins_kotlin_compose.ui.theme.colorPrimary

@Composable
fun NeighboursListScreen(
    onSelectedNeighbour: (Int) -> Unit,
    onDeleteClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NeighboursViewModel = hiltViewModel(),
    onFabClicked: () -> Unit,
    isFavoritePage: Boolean = false
) {
    val neighbours by viewModel.neighboursList.collectAsState()
    val favoritesNeighbours by viewModel.favoritesNeighboursList.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClicked,
                containerColor = colorPrimary,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person_add_white_24dp),
                    contentDescription = stringResource(R.string.add),
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = modifier.padding(paddingValues)
        ) {
            items(
                items = if(isFavoritePage) favoritesNeighbours else neighbours,
                key = { it.idNeighbour }
            ) { neighbour ->
                NeighbourItem(
                    neighbour = neighbour,
                    onClick = { onSelectedNeighbour(neighbour.idNeighbour) },
                    onDeleteClick = {onDeleteClicked(neighbour.idNeighbour)}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NeighbourListPreview() {
    NeighboursListScreen(onSelectedNeighbour = {}, onFabClicked = {}, onDeleteClicked = {})
}