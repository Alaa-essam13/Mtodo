package com.example.mtodo.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtodo.data.TaskEntity
import com.example.mtodo.data.TodoDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class TodoViewModel(private val todoDao: TodoDao) : ViewModel() {
    private val _todos = MutableStateFlow<List<TaskEntity>>(emptyList())
    val todos: StateFlow<List<TaskEntity>> = _todos

    init {
        viewModelScope.launch {
            todoDao.getTasksinSameDate(LocalDate.now()).collect {
                _todos.value = it
            }
        }
    }


    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            todoDao.updateTask(task)
        }
    }

    fun getTasksDone(task: TaskEntity) {
        viewModelScope.launch {
            todoDao.getCompletedTasks()
        }
    }

    fun getTasksinDate(task: TaskEntity) {
        viewModelScope.launch {
            todoDao.getTasksinSameDate(task.date)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            todoDao.deleteTask(task)
        }
    }

    fun getAllTasks(): MutableStateFlow<List<TaskEntity>> {
        val currentDate = LocalDate.now()
        val todoss = MutableStateFlow<List<TaskEntity>>(emptyList())
        viewModelScope.launch {
            todoDao.getAllTasks().collect { tasks ->
                tasks.forEach { task ->
                    val taskDate =
                        LocalDate.parse(task.date.toString()) // Assuming date is stored as a String in "YYYY-MM-DD" format
                    if (taskDate.isBefore(currentDate)) {
                        deleteTask(task) // Call delete function for tasks before the current date
                    }
                }
                todoss.value = tasks.filter { task ->
                    val taskDate = LocalDate.parse(task.date.toString())
                    taskDate.isEqual(currentDate) || taskDate.isAfter(currentDate)
                } // Keep only tasks from today or future

            }
        }

        return todoss
    }


}