package com.kuloma.testexample.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kuloma.testexample.ToDoEntity
import com.kuloma.testexample.presentation.main.ui.CalendarScreen
import com.kuloma.testexample.presentation.main.ui.ToDoListScreen
import com.kuloma.testexample.presentation.theme.Blue
import com.kuloma.testexample.presentation.theme.VeryDarkBlue

@Composable
fun MainRoot(viewModel: MainViewModel,
             onItemClick : (ToDoEntity) -> Unit,
             onDateClick : (String) -> Unit,
             onClickAdd : () -> Unit,
             toDoList: List<ToDoEntity>,
             dateWithToDo: Set<String>
){
    var changedDate by remember {
        mutableStateOf("")
    }

    Box{
        Column {
            CalendarScreen(
                onDateClick = { date ->
                    changedDate = date
                    onDateClick(date)
                },
                datesWithToDo = dateWithToDo
            )
            Text(
                modifier = Modifier.fillMaxWidth().background(VeryDarkBlue),
                textAlign = TextAlign.Center,
                color = Color.White,
                text = changedDate
            )
            ToDoListScreen(
                entities = toDoList,
                toClickItem = { entity ->
                    onItemClick(entity)
                },
                viewModel = viewModel
            )
        }

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ){
            FloatingActionButton(onClick = {
                onClickAdd()
//                val entity = ToDoEntity(
//                    name = "Дела",
//                    description = "блаблаблабла",
//                    dateStart = "1733664511",
//                    dateFinish = "1733664511",
//                    dayDate = changedDate
//                )
//                viewModel.addItem(entity)
            },
                modifier = Modifier.padding(20.dp),
                containerColor = Blue,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}
