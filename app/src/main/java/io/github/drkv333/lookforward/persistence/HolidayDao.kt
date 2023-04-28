package io.github.drkv333.lookforward.persistence

import androidx.room.*
import io.github.drkv333.lookforward.model.Holiday
import java.util.UUID

@Dao
interface HolidayDao {
    @Insert
    suspend fun insert(vararg holidays: Holiday)

    @Update
    suspend fun update(vararg holidays: Holiday)

    @Delete
    suspend fun delete(vararg holidays: Holiday)

    @Query("SELECT * FROM holiday ORDER BY date")
    suspend fun getAll(): Array<Holiday>

    @Query("SELECT * FROM holiday WHERE id = :id")
    suspend fun get(id: UUID): Holiday
}
