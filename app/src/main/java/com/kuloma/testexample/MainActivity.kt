package com.kuloma.testexample

import android.annotation.SuppressLint
import android.content.pm.ModuleInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.kuloma.testexample.ui.theme.Blue
import com.kuloma.testexample.ui.theme.DarkBlue
import com.kuloma.testexample.ui.theme.TestExampleTheme
import com.kuloma.testexample.ui.theme.VeryDarkBlue
import java.util.Calendar

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestExampleTheme {
                CalendarScreen(viewModel)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TestExampleTheme {
//        val entity = ToDoEntity(
//            id = 1,
//            date_start = "139128338",
//            date_finifsh = "13413251",
//            name = "качаца",
//            description = "Анжумания бегит пресс качат"
//        )
//        ToDoCard(entity)
////        CalendarScreen()
//    }
//}

@Composable
fun CalendarScreen(viewModel: MainViewModel) {
    var date by remember {
        mutableStateOf("")
    }
    val listState = rememberLazyListState()

    //здесь будет вызов функции которая возвращает список с днями в которых есть события
    val listToDo: MutableList<ToDoEntity> = mutableListOf()
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


    val calendars: ArrayList<CalendarDay> = ArrayList()
    val calendar = Calendar.getInstance()
    calendar.set(2024, 11, 20)
    val calendarDay = CalendarDay(calendar)
    calendarDay.imageResource = R.drawable.baseline_fiber_manual_record_24

    calendars.add(calendarDay)

    Column {
        AndroidView(factory = { CalendarView(it) }, update = {

            it.setCalendarDays(calendars)
            it.setHeaderColor(R.color.veryDarkBlue)

            it.setOnCalendarDayClickListener(
                object : OnCalendarDayClickListener {
                    override fun onClick(calendarDay: CalendarDay) {
//                        Calendar.MONTH)+1
                        date = calendarDay.calendar.get(Calendar.DAY_OF_MONTH).toString()
                    }
                })
        })

        ToDoListScreen(listToDo, listState, viewModel)

    }
}

@Composable
fun ToDoListScreen(entities: List<ToDoEntity>, state: LazyListState, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DarkBlue)
    ) {

        LazyColumn(state = state) {
            items(entities) { entity ->
                ToDoCard(entity, viewModel)
            }
        }
    }
}

@Composable
fun ToDoCard(entity: ToDoEntity,viewModel: MainViewModel) {
    val startTime = viewModel.formatTimestamp(entity.date_start.toLong())
    val endTime = viewModel.formatTimestamp(entity.date_finifsh.toLong())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                top = 8.dp,
                start = 4.dp,
                end = 4.dp
            )
            .clickable { /* Обработка клика нах */ },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = VeryDarkBlue)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .width(10.dp) // Ширина линии
                    .fillMaxHeight() // Заполнить высоту
                    .background(Blue) // Цвет линии
            )
            Text(
                text = entity.name,
                fontSize = 20.sp,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .width(1.dp) // Ширина линии
                        .fillMaxHeight() // Заполнить высоту
                        .background(Color.White) // Цвет линии
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier.padding(end = 10.dp)
                ) {
                    Text(
                        text = startTime,
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = endTime,
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

