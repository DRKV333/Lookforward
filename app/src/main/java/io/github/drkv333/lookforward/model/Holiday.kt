package io.github.drkv333.lookforward.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "holiday")
data class Holiday(
    val title: String,
    val description: String,
    val date: Date,
    @PrimaryKey val id: UUID = UUID.randomUUID(),
)