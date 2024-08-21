package com.haksoftware.entrevoisins_kotlin_compose.ui

import androidx.compose.foundation.Image
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
fun NeighbourItemList(modifier: Modifier = Modifier, neigbour: NeighbourEntity) {
    Card(
        modifier = Modifier
            .height(110.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        )  {
            NeighbourImage(neigbour= neigbour)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                Row(modifier = modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Username(neigbour= neigbour)
                    Spacer(modifier = Modifier.weight(1f))
                    DeleteButton {

                    }
                }
            }
        }
    }
}
@Composable
fun NeighbourImage(modifier: Modifier = Modifier, neigbour: NeighbourEntity) {
    AsyncImage(
        model = neigbour.pathPhoto,
        contentDescription = stringResource(R.string.profile_photo),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Username(modifier: Modifier = Modifier, neigbour: NeighbourEntity) {
    Text(
        text = neigbour.userName,
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
        modifier = Modifier
            .size(width = 48.dp, height = 48.dp),
        content = {
            // Specify the icon using the icon parameter
            Image(painter = painterResource(id = R.drawable.ic_delete_white_24dp), contentDescription = null)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NeighbourPreview() {
    Entrevoisins_Kotlin_ComposeTheme {
        NeighbourItemList(
            modifier = Modifier,
            neigbour = NeighbourEntity(
                1,
                "Caroline",
                "",
                "",
                "",
                "https://i.pravatar.cc/150?u=a042581f4e29026704d",
                false
            )
        )
    }

}