package com.haksoftware.entrevoisins_kotlin_compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.haksoftware.entrevoisins_kotlin_compose.R
import com.haksoftware.entrevoisins_kotlin_compose.data.entity.NeighbourEntity
import com.haksoftware.entrevoisins_kotlin_compose.ui.theme.Entrevoisins_Kotlin_ComposeTheme

@Composable
fun NeighbourItem(
    modifier: Modifier = Modifier,
    neighbour: NeighbourEntity,
    onClick: (Int) -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(110.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick(neighbour.idNeighbour) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NeighbourImage(neighbour = neighbour)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Username(neighbour = neighbour)
                    Spacer(modifier = Modifier.weight(1f))
                    DeleteButton(onDeleteClick)
                }
            }
        }
    }
}

@Composable
fun NeighbourImage(modifier: Modifier = Modifier, neighbour: NeighbourEntity) {
    AsyncImage(
        model = neighbour.pathPhoto,
        contentDescription = stringResource(R.string.profile_photo),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Username(modifier: Modifier = Modifier, neighbour: NeighbourEntity) {
    Text(
        text = neighbour.userName,
        modifier = modifier,
        style = typography.titleLarge
    )
}

@Composable
fun DeleteButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_delete_white_24dp),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NeighbourPreview() {
    Entrevoisins_Kotlin_ComposeTheme {
        NeighbourItem(
            modifier = Modifier,
            neighbour = NeighbourEntity(
                idNeighbour = 1,
                userName = "Caroline",
                phoneNumber = "",
                email = "",
                description = "",
                pathPhoto = "https://i.pravatar.cc/150?u=a042581f4e29026704d",
                isFavorite = false
            ),
            onClick = {}, // Exemple de callback onClick
            onDeleteClick = {} // Exemple de callback onDeleteClick
        )
    }
}
