package com.example.mtodo.pages.addpage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtodo.Components.MyTextField
import com.example.mtodo.data.TaskEntity
import com.example.mtodo.data.color
import com.example.mtodo.pages.TodoViewModel
import com.example.mtodo.ui.theme.BackColor
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun addPage(viewModel: TodoViewModel,onSave:()->Unit) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by remember {
        mutableStateOf(LocalTime.now())
    }
    var pickedTime2 by remember {
        mutableStateOf(LocalTime.now().plusHours(1))
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickedDate)
        }
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("hh:mm a").format(pickedTime)
        }
    }
    val formattedTime2 by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("hh:mm a").format(pickedTime2)
        }
    }
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()
    val timeDialogState2 = rememberMaterialDialogState()
    var colorIndex by remember {
        mutableIntStateOf(0)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .background(BackColor)
            .padding(top = 60.dp, start = 25.dp, end = 25.dp)
    ) {
        Text(text = "Add Your Task", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))
        MyTextField(name = "Task Title", s = title) { title = it }
        Spacer(modifier = Modifier.height(30.dp))
        MyTextField(name = "Description", s = description) { description = it }
        Spacer(modifier = Modifier.height(30.dp))
        datepick(dateDialogState)
        Text(text = formattedDate)
        Spacer(modifier = Modifier.height(30.dp))
        timePick(timeDialogState, formattedTime, timeDialogState2, formattedTime2)
        Spacer(modifier = Modifier.height(30.dp))
        ColorSection(colorIndex){colorIndex=it}
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            enabled = title.isNotBlank() ||description.isNotBlank(),
            onClick = {
            viewModel.updateTask(
                TaskEntity(
                    title = title,
                    description = description,
                    startTime = formattedTime,
                    endTime = formattedTime2,
                    duration = "${Duration.between(pickedTime, pickedTime2).toHours()}H ${Duration.between(pickedTime, pickedTime2).toMinutes()%60} Min",
                    colorId = colorIndex,
                    date = pickedDate
                )
            )
                onSave()
        }) {
            Text(text = "Save")
        }
    }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("Ok") {}
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            allowedDateValidator = { it >= LocalDate.now() }) {
            pickedDate = it
        }
    }

    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok") {}
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a time",
            timeRange = LocalTime.MIN..LocalTime.MAX
        ) {
            pickedTime = it
        }
    }
    MaterialDialog(
        dialogState = timeDialogState2,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a time",
            timeRange = pickedTime.plusHours(1)..LocalTime.MAX
        ) {
            pickedTime2 = it
        }
    }
}

@Composable
private fun ColorSection(colorIndex: Int,onSelect:(Int)->Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier =
        Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        itemsIndexed(color) { index, it ->
            Box(
                contentAlignment = Alignment.Center,
                modifier =
                Modifier
                    .size(30.dp)
                    .background(it.secondaryColor)
                    .clickable {
                        onSelect(index)
                    }
                    .border(
                        if (index == colorIndex) BorderStroke(
                            2.dp,
                            Color.Blue
                        ) else BorderStroke(0.dp, Color.Transparent)
                    )
            ) {

            }
        }
    }
}

@Composable
private fun timePick(
    timeDialogState: MaterialDialogState,
    formattedTime: String,
    timeDialogState2: MaterialDialogState,
    formattedTime2: String
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { timeDialogState.show() }) {
                    Text(text = "Pick Start Time")
                }
                Text(text = formattedTime)
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { timeDialogState2.show() }) {
                    Text(text = "Pick End Time")
                }
                Text(text = formattedTime2)
            }
        }
    }
}

@Composable
private fun datepick(dateDialogState: MaterialDialogState) {
    Button(onClick = {
        dateDialogState.show()
    }) {
        Text(text = "Pick date")
    }
}

//@Preview(showSystemUi = true)
//@Composable
//private fun po() {
//    addPage(viewModel)
//}