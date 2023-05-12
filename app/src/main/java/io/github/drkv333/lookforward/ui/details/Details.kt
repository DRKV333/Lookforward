package io.github.drkv333.lookforward.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.drkv333.lookforward.model.iconResource
import io.github.drkv333.lookforward.navigateReplace
import java.text.DateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    var editing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(viewModel.title) },
                actions = {
                    if (editing) {
                        IconButton(onClick = {
                            viewModel.save()
                            editing = false
                        }) {
                            Icon(imageVector = Icons.Default.Done, contentDescription = "Done", tint = Color.White)
                        }
                    } else {
                        IconButton(onClick = {
                            viewModel.delete()
                            navController.navigateReplace("main")
                        }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                        }

                        IconButton(onClick = { editing = true }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateReplace("main") }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = viewModel.kind.iconResource),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(end = 10.dp)
                )

                if (editing) {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Select Type")
                    }
                }
            }

            if (editing) {
                OutlinedTextField(
                    value = viewModel.title,
                    onValueChange = { viewModel.title = it },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(textColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                OutlinedTextField(
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it },
                    colors = TextFieldDefaults.textFieldColors(textColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            } else {
                Text(
                    viewModel.description,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Row {
                Text(DateFormat.getDateInstance(DateFormat.DEFAULT).format(viewModel.date),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )

                if (editing) {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select Date"
                        )
                    }
                }
            }

            if (!editing) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Switch(
                        checked = false,
                        onCheckedChange = { /* TODO */ },
                        modifier = Modifier.padding(10.dp)
                    )
                    Text("Remind me")
                }
            }
        }
    }
}