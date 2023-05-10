package io.github.drkv333.lookforward.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.drkv333.lookforward.R
import io.github.drkv333.lookforward.model.Holiday
import java.text.DateFormat
import java.util.*

private val testItems = arrayOf(
    Holiday("New Year's Day 1", "", Date(2024, 1, 1)),
    Holiday("New Year's Day 2", "", Date(2024, 1, 1)),
    Holiday("New Year's Day 3", "", Date(2024, 1, 1))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    viewModel: MainViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Lookforward") },
                actions = {
                    var showMenu by remember { mutableStateOf(false) }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Create", tint = Color.White)
                    }

                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More", tint = Color.White)
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(text = { Text("Login") }, onClick = { /*TODO*/ })
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .border(4.dp, Color.Black)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.othericon),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(end = 10.dp)
                )

                Column {
                    Text("New Year's Eve", fontSize = 25.sp)
                    Text("3024.01.01", fontSize = 20.sp)
                    Text("In 365 days", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }

            LazyColumn {
                items(testItems) { item ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp)
                            .border(1.dp, Color.Black)
                            .padding(5.dp)
                            .clickable {
                                // TODO
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.nationalicon),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                        )
                        Text(item.title)
                        Text(DateFormat.getDateInstance(DateFormat.SHORT).format(item.date))
                    }
                }
            }
        }
    }
}