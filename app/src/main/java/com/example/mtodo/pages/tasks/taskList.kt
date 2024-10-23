package com.example.mtodo.pages.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtodo.R
import com.example.mtodo.pages.TodoViewModel
import com.example.mtodo.pages.homepage.TaskComponent
import java.time.LocalDate


@Composable
fun TaskList(viewModel: TodoViewModel
             ,modifier: Modifier = Modifier) {
    val todos = viewModel.getAllTasks()
    val sortedTodos by todos.collectAsState(initial = emptyList())
    var baseDate by remember {
        mutableStateOf(LocalDate.now().minusDays(1))
    }
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
                text = "Tasks",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        itemsIndexed(sortedTodos) { index, it ->
            var isSwiped by remember { mutableStateOf(false) }
            if(baseDate!=it.date) {
                baseDate = it.date
                Text(
                    text = baseDate.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
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
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(50.dp),
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