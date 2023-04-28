package io.github.drkv333.lookforward.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class MyHoliday (
    val id: String,
    val title: String,
    val description: String,
    val date: String
)