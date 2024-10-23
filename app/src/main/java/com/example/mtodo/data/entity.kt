package com.example.mtodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "Tasks-db")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val title: String,
    val description: String,
    val startTime:String,
    val endTime: String,
    val duration: String,
    val colorId:Int,
    val date: LocalDate,
    val completed: Boolean=false
)