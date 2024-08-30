package com.haksoftware.entrevoisins_kotlin_compose.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.haksoftware.entrevoisins_kotlin_compose.viewmodel.NeighboursViewModel
import com.haksoftware.entrevoisins_kotlin_compose.R
import com.haksoftware.entrevoisins_kotlin_compose.data.entity.NeighbourEntity
import com.haksoftware.entrevoisins_kotlin_compose.ui.theme.gold
import com.haksoftware.entrevoisins_kotlin_compose.ui.theme.pink

@Composable
fun NeighbourDetailsScreen(
    neighbourId: Int,
    viewModel: NeighboursViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var neighbour by remember { mutableStateOf<NeighbourEntity?>(null) }
    var isFavorite by remember { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(neighbourId) {
        val loadedNeighbour = viewModel.getNeighbour(neighbourId)
        neighbour = loadedNeighbour
        isFavorite = loadedNeighbour.isFavorite
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (neighbour != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                item {
                    Box(modifier = Modifier.height(280.dp)) {
                        AsyncImage(
                            model = neighbour!!.pathPhoto,
                            contentDescription = stringResource(id = R.string.profile_photo),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = neighbour!!.userName,
                            color = Color.White,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 15.dp, bottom = 25.dp)
                        )
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 15.dp)
                            .background(Color.White)
                    ) {
                        Text(
                            text = neighbour!!.userName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .height(50.dp)
                        )

                        InfoRow(
                            icon = R.drawable.ic_location_white_24dp,
                            text = neighbour!!.email
                        )

                        InfoRow(
                            icon = R.drawable.baseline_local_phone_24,
                            text = neighbour!!.phoneNumber
                        )

                        InfoRow(
                            icon = R.drawable.ic_web__white_24dp,
                            text = neighbour!!.email
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                            .background(Color.White)
                    ) {
                        Text(
                            text = stringResource(id = R.string.about_me),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                                .height(50.dp)
                        )

                        Text(
                            text = neighbour!!.description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp, vertical = 7.dp)
                                .heightIn(min = 150.dp)
                        )
                    }
                }
            }
            FloatingActionButton(
                onClick = { neighbour?.let {
                    val updatedNeighbour = it.copy(isFavorite = !it.isFavorite)
                    viewModel.updateNeighbour(updatedNeighbour) { success ->
                        if (success) {
                            isFavorite = !isFavorite
                            Toast.makeText(context,
                                context.getString(R.string.neighbour_favorite_list_updated_successfully), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context,
                                context.getString(R.string.failed_to_update_favorite_neighbour_list), Toast.LENGTH_SHORT).show()
                        }
                    }
                }},
                containerColor = Color.White,
                contentColor = if (isFavorite) gold else Color.Gray,
                shape = CircleShape,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = (240).dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = if (isFavorite)
                        painterResource(id = R.drawable.ic_star_white_24dp)
                    else
                        painterResource(id = R.drawable.ic_star_border_white_24dp),
                    contentDescription = stringResource(id = R.string.favorite)
                )
            }
        }
    }
}

@Composable
fun InfoRow(
    icon: Int,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = pink,
            modifier = Modifier.size(19.dp)
        )
        Text(
            text = text,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayNeighbourScreen() {
    NeighbourDetailsScreen(
        neighbourId = 1,
        onBackClicked = {}
    )
}
