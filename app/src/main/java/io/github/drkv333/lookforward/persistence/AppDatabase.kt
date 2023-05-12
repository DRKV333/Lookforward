package io.github.drkv333.lookforward.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.drkv333.lookforward.model.Holiday

@Database(entities = [Holiday::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun holidayDao(): HolidayDao
}