package com.haksoftware.entrevoisins_kotlin_compose.ui

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.haksoftware.entrevoisins_kotlin_compose.viewmodel.NeighboursViewModel
import com.haksoftware.entrevoisins_kotlin_compose.R
import com.haksoftware.entrevoisins_kotlin_compose.data.entity.NeighbourEntity

@Composable
fun AddNeighbourScreen(
    onBackClicked: () -> Unit,
    viewModel: NeighboursViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val timeMillis = remember { System.currentTimeMillis() }
    val photo = "https://i.pravatar.cc/150?u=$timeMillis"

    val context = LocalContext.current

    val isSaveEnabled = name.isNotBlank() && phoneNumber.isNotBlank() && email.isNotBlank() &&
            description.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()


    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 18.dp),
            contentPadding = PaddingValues(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                // Centrage horizontal de l'image
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    AsyncImage(
                        model = photo,
                        contentDescription = stringResource(R.string.profile_photo),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                }

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = stringResource(R.string.name)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text(text = stringResource(R.string.phone_number)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = stringResource(R.string.email)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = stringResource(id = R.string.about_me)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp),
                    maxLines = 4
                )

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = {
                            val neighbour = NeighbourEntity(
                                idNeighbour = 0,
                                userName = name,
                                description = description,
                                email = email,
                                phoneNumber = phoneNumber,
                                pathPhoto = photo,
                                isFavorite = false
                            )

                            viewModel.insertNeighbour(neighbour) { success ->
                                if (success) {
                                    Toast.makeText(context, "Neighbour added successfully", Toast.LENGTH_SHORT).show()
                                    onBackClicked() // Retourner seulement si l'ajout est réussi
                                } else {
                                    Toast.makeText(context, "Failed to add neighbour", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        modifier = Modifier
                            .padding(top = 20.dp),
                        enabled = isSaveEnabled,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(text = stringResource(R.string.save))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddNeighbourScreen() {
    AddNeighbourScreen({})
}
