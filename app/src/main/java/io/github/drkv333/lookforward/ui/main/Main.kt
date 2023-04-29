package io.github.drkv333.lookforward.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.drkv333.lookforward.network.dto.HolidayResponse
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import io.github.drkv333.lookforward.network.dto.HolidayResponseResponseHolidays
import kotlinx.coroutines.launch

@Composable
fun Main(
    viewModel: MainViewModel = viewModel()
) {
    val list: HolidayResponse? by viewModel.holidayList.collectAsState(null)
    val scope = rememberCoroutineScope()

    Column {
        Button(onClick = { scope.launch { viewModel.insertTest() } }) {
            Text("Insert test item")
        }
        LazyColumn {
            items(list?.response?.holidays ?: arrayOf<HolidayResponseResponseHolidays>()) { item ->
                Text(item.name)
            }
        }
    }
}