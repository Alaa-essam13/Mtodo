package com.example.mtodo.Event

sealed interface AddEvent {
    data class SetTitle(val title: String) : AddEvent
    data class SetDescription(val description: String) : AddEvent
    data class SetDueDate(val dueDate: String) : AddEvent
    data class SetStartTime(val startTime: String): AddEvent
    data class SetEndTime(val endTime: String): AddEvent
}