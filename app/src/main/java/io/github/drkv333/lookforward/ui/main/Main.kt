package io.github.drkv333.lookforward.ui.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.drkv333.lookforward.network.dao.HolidayResponse
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import io.github.drkv333.lookforward.network.dao.HolidayResponseResponseHolidays

@Composable
fun Main(
    viewModel: MainViewModel = viewModel()
) {
    val list: HolidayResponse? by viewModel.holidayList.collectAsState(null)
    LazyColumn {
        items(list?.response?.holidays ?: arrayOf<HolidayResponseResponseHolidays>()) { item ->
            Text(item.name)
        }
    }
}