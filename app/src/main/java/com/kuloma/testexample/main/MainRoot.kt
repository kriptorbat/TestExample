package com.kuloma.testexample.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.kuloma.testexample.R
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
}
