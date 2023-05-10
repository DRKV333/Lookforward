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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.drkv333.lookforward.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details() {
    var editing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("New Year's Day") },
                actions = {
                    if (editing) {
                        IconButton(onClick = { editing = false }) {
                            Icon(imageVector = Icons.Default.Done, contentDescription = "Done", tint = Color.White)
                        }
                    } else {
                        IconButton(onClick = { editing = false }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                        }

                        IconButton(onClick = { editing = true }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
                        }
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
                    painter = painterResource(id = R.drawable.othericon),
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
                    value = "New Year's Day",
                    onValueChange = { /* TODO */ },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                OutlinedTextField(
                    value = "This is very long bit of text that will hopefully be broken into multiple separate lines.",
                    onValueChange = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            } else {
                Text(
                    "This is very long bit of text that will hopefully be broken into multiple separate lines.",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Row {
                Text("2024.01.01",
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