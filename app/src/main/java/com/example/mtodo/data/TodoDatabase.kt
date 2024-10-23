package com.example.mtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TaskEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(TodoConverter::class)
abstract class TodoDatabase:RoomDatabase() {
    abstract val dao :TodoDao
}