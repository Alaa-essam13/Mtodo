package com.example.mtodo.pages.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtodo.R
import com.example.mtodo.data.TaskEntity
import com.example.mtodo.data.color
import com.example.mtodo.pages.TodoViewModel
import com.example.mtodo.ui.theme.BackColor
import com.example.mtodo.ui.theme.PrimaryColor
import com.example.mtodo.ui.theme.PrimaryColor2
import com.example.mtodo.ui.theme.PrimaryColor3
import com.example.mtodo.ui.theme.PrimaryColor4
import com.example.mtodo.ui.theme.PrimaryColor5
import com.example.mtodo.ui.theme.PrimaryColor6
import com.example.mtodo.ui.theme.SecondaryColor
import com.example.mtodo.ui.theme.SecondaryColor2
import com.example.mtodo.ui.theme.SecondaryColor3
import com.example.mtodo.ui.theme.SecondaryColor4
import com.example.mtodo.ui.theme.SecondaryColor5
import com.example.mtodo.ui.theme.SecondaryColor6
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

//data class task(val name: String, val start: String, val end: String, val duration: String)


@Composable
fun Home(viewModel: TodoViewModel,onTasksClick:()->Unit, onAddClick: () -> Unit) {
//    val tasks = listOf(
//        task("Task 1", "08:00", "09:00", "1 hour"),
//        task("Task 2", "09:00", "10:00", "1 hour"),
//        task("Task 3", "10:00", "11:00", "1 hour"),
//        task("Task 4", "11:00", "12:00", "1 hour"),
//        task("Task 5", "12:00", "13:00", "1 hour"),
//        task("Task 6", "13:00", "14:00", "1 hour"),
//    )
    val todos by viewModel.todos.collectAsState(initial = emptyList())

    Column(
        Modifier
            .fillMaxSize()
            .background(BackColor)

    ) {
        TopOfScreen(onTasksClick = {onTasksClick()}) { onAddClick() }
        DayAndTimeCard()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(10.dp)
                .padding(top = 20.dp)
        ) {
            item {
                Text(
                    text = "Today tasks",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(todos) { it ->
                var isSwiped by remember { mutableStateOf(false) }
                Box(Modifier.fillMaxWidth()) {
                    TaskComponent(
                        it,
                        viewModel,
                        modifier = Modifier
                            .offset(x = if (isSwiped) (-80).dp else 0.dp)
                            .pointerInput(Unit) {
                                detectHorizontalDragGestures { change, dragAmount ->
                                    if (dragAmount < -50) {
                                        isSwiped = true
                                    } else if (dragAmount > 50) {
                                        isSwiped = false
                                    }
                                    change.consume()
                                }
                            }
                    )
                    if (isSwiped) {
                        Box(
                            contentAlignment = Alignment.CenterEnd, modifier = Modifier
                                .height(176.dp)
                                .fillMaxWidth(.95f)
                                .padding(top = 10.dp)
                        ) {
                            IconButton(
                                modifier = Modifier.fillMaxHeight().width(50.dp),
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = Color.Red
                                ),
                                onClick = {
                                    viewModel.deleteTask(it)
                                    isSwiped = !isSwiped
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.garbage),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(30.dp)
                                )
                            }

                        }
                    }
                }





                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}


@Composable
fun TaskComponent(
    todo: TaskEntity, vm: TodoViewModel, modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .height(176.dp)
            .clip(RoundedCornerShape(26.dp))
            .alpha(.7f)
            .background(color[todo.colorId].primaryColor)
            .padding(18.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "You Have \n A ${todo.title}",
                color = color[todo.colorId].secondaryColor,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
            Image(
                painter = painterResource(id = if (todo.completed) R.drawable.checked else R.drawable.uncompleted),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { vm.updateTask(todo.copy(completed = !todo.completed)) })
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Column {
                Text(text = todo.startTime, color = Color.Black, fontSize = 20.sp)
                Text(text = "start", color = Color.Black, fontSize = 20.sp)
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(75.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(color[todo.colorId].secondaryColor)
            ) {
                Text(
                    text = todo.duration,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Column {
                Text(text = todo.endTime, color = Color.Black, fontSize = 20.sp)
                Text(text = "End", color = Color.Black, fontSize = 20.sp)
            }
        }
    }


}

@Composable
private fun TopOfScreen(onTasksClick:()->Unit,onAddClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = "Today", color = Color.White)
        }
        Spacer(modifier = Modifier.width(25.dp))
        Button(
            onClick = onTasksClick,
            modifier = Modifier
                .border(1.dp, Color.Gray, CircleShape),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(text = "All Tasks", color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.LightGray, CircleShape)
//                .padding(8.dp)
                .clickable { onAddClick() }, contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = Color.Black)
        }
    }
}

@Composable
private fun DayAndTimeCard() {
    val currentDate = LocalDate.now()
    val weekday = currentDate.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
    val dayOfMonth = currentDate.dayOfMonth.toString()
    val month = currentDate.month.getDisplayName(TextStyle.SHORT, Locale.getDefault()).uppercase()
    val currentTime = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    val time = remember { mutableStateOf(currentTime.format(formatter)) }
    val local: Locale = Locale.getDefault()
    val country = local.displayCountry
    LaunchedEffect(Unit) {
        while (true) {
            time.value = LocalTime.now().format(formatter)
            delay(1000L)
        }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        ) {
            Text(text = weekday, fontSize = 22.sp, fontWeight = FontWeight.Medium)
            Text(text = dayOfMonth, fontSize = 100.sp, fontWeight = FontWeight.Bold)
            Text(text = month, fontSize = 48.sp, fontWeight = FontWeight.Bold)
        }
        VerticalDivider(color = Color.Black)
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Text(text = time.value, fontSize = 24.sp, fontWeight = FontWeight.Medium)
            Text(text = country, fontSize = 22.sp, fontWeight = FontWeight.Normal)
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//private fun myprev() {
//
//    Home()
//}