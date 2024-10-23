package com.example.mtodo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate


@Dao
interface TodoDao {


    @Delete
    suspend fun deleteTask(task: TaskEntity)


    @Upsert
    suspend fun updateTask(task: TaskEntity)

    @Query("SELECT * FROM `tasks-db` order by date")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM `tasks-db` WHERE completed = true ")
    fun getCompletedTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM `tasks-db` WHERE date = :date")
    fun getTasksinSameDate(date: LocalDate): Flow<List<TaskEntity>>

}