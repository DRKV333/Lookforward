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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.drkv333.lookforward.R
import io.github.drkv333.lookforward.model.iconResource
import io.github.drkv333.lookforward.navigateReplace
import java.text.DateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
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
                        if (viewModel.isLoggedIn) {
                            DropdownMenuItem(text = { Text("Logout") }, onClick = {
                                viewModel.logout()
                                navController.navigateReplace("login")
                            })
                        } else {
                            DropdownMenuItem(text = { Text("Login") }, onClick = {
                                navController.navigateReplace("login")
                            })
                        }
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            if (viewModel.loading) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .border(4.dp, Color.Black)
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = viewModel.firstHoliday?.kind?.iconResource ?: R.drawable.othericon),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .width(150.dp)
                            .height(150.dp)
                            .padding(end = 10.dp)
                    )

                    Column {
                        Text(viewModel.firstHoliday?.title ?: "", fontSize = 25.sp)
                        Text(viewModel.firstHoliday?.let { DateFormat.getDateInstance(DateFormat.DEFAULT).format(it.date) } ?: "", fontSize = 20.sp)
                        Text("In ${viewModel.firstHolidayTimeLeft} days", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }

                LazyColumn {
                    items(viewModel.holidayList) { item ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(1.dp)
                                .border(1.dp, Color.Black)
                                .padding(5.dp)
                                .clickable {
                                    navController.navigateReplace("details?id=${item.id}")
                                }
                        ) {
                            Image(
                                painter = painterResource(id = item.kind.iconResource),
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
}