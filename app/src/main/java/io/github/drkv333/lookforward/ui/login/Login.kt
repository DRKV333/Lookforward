package io.github.drkv333.lookforward.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.drkv333.lookforward.R

@Composable
fun Login(viewModel: LoginViewModel = viewModel()) {
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
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text("Maybe later...")
                    }

                    OutlinedButton(onClick = { /*TODO*/ },) {
                        Row {
                            Text("Login")
                            Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "")
                        }
                    }
                }
            }
        }
    }
}