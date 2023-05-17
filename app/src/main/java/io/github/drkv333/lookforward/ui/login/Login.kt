package io.github.drkv333.lookforward.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.drkv333.lookforward.R
import io.github.drkv333.lookforward.navigateReplace

@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var showEverything by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel) {
        if (viewModel.autoLogin()) {
            navController.navigateReplace("main")
        } else {
            showEverything = true
        }
    }

    if (showEverything) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.loginbg),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(10.dp)
                ) {
                    OutlinedTextField(
                        value = viewModel.username,
                        onValueChange = { viewModel.username = it },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black),
                        label = { Text("Username") }
                    )

                    OutlinedTextField(
                        value = viewModel.password,
                        onValueChange = { viewModel.password = it },
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black),
                        label = { Text("Password") }
                    )

                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        TextButton(onClick = {
                            navController.navigateReplace("main")
                        }) {
                            Text("Maybe later...")
                        }

                        OutlinedButton(onClick = {
                            viewModel.login()
                            navController.navigateReplace("main")
                        }) {
                            Row {
                                Text("Login")
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}