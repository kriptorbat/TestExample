package com.kuloma.testexample.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.kuloma.testexample.ToDoEntity
import com.kuloma.testexample.main.ui.CalendarScreen
import com.kuloma.testexample.main.ui.ToDoListScreen
import com.kuloma.testexample.ui.theme.Blue
import com.kuloma.testexample.ui.theme.DarkBlue
import com.kuloma.testexample.ui.theme.VeryDarkBlue
import java.util.Calendar

@Composable
fun MainRoot(viewModel: MainViewModel){
    //здесь будет вызов функции которая возвращает список с днями в которых есть события
    val listToDo: MutableList<ToDoEntity> = mutableListOf()

    val items by remember { mutableStateOf(listOf<String>()) }
    var chainedDate by remember {
        mutableStateOf("")
    }

    val d1 = ToDoEntity(
        id = 1,
        date_start = "1733664511",
        date_finifsh = "1733664523",
        name = "дело",
        description = "савбфвафылав"
    )
    val d2 = ToDoEntity(
        id = 1,
        date_start = "1733664511",
        date_finifsh = "1733664523",
        name = "дело2",
        description = "савбфвафылав2"
    )
    val d3 = ToDoEntity(
        id = 1,
        date_start = "1733664511",
        date_finifsh = "1733664523",
        name = "дело2",
        description = "савбфвафылав2"
    )
    listToDo.add(d1)
    listToDo.add(d2)
    listToDo.add(d3)
    Box{
        Column {
            CalendarScreen(
                toDateClick = { date ->
                    chainedDate = date
                },
                datesWithToDo = items
            )
            ToDoListScreen(
                entities = listToDo,
                toClickItem = {},
                viewModel = viewModel
            )
        }
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ){
            FloatingActionButton(onClick = {
                Log.d("fab", "hi")
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
