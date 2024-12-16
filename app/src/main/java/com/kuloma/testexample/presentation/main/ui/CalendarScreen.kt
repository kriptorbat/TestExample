package com.kuloma.testexample.presentation.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import com.kuloma.testexample.presentation.theme.VeryDarkBlue
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun CalendarScreen(
    selectedDate: String,
    onDateClick: (String) -> Unit,
    datesWithToDo: Set<String>,
) {
    val listMounts = listOf("Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь")

    val currentCalendar = remember { Calendar.getInstance() }
    var currentMonth by remember { mutableStateOf(currentCalendar.get(Calendar.MONTH)) }
    var currentYear by remember { mutableStateOf(currentCalendar.get(Calendar.YEAR)) }

    fun changeMonth(increment: Int) {
        currentMonth += increment
        if (currentMonth < 0) {
            currentMonth = 11
            currentYear -= 1
        } else if (currentMonth > 11) {
            currentMonth = 0
            currentYear += 1
        }
    }

    currentCalendar.set(Calendar.MONTH, currentMonth)
    currentCalendar.set(Calendar.YEAR, currentYear)

    val daysInMonth = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val daysList = (1..daysInMonth).map { day ->
        val date = String.format("%04d.%02d.%02d", currentYear, currentMonth + 1, day)
        date to (date in datesWithToDo)
    }

    val year = SimpleDateFormat("yyyy", Locale.getDefault()).format(currentCalendar.time)
    val mount = listMounts[currentCalendar.get(Calendar.MONTH)]


    Column {
        Row(
            modifier = Modifier.fillMaxWidth().background(VeryDarkBlue),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { changeMonth(-1) }) {
                Icon(Icons.Default.ArrowBack,
                    contentDescription = "Предыдущий месяц",
                    tint = Color.White
                )
            }

            Text(
                text = "$year $mount",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp),
                color = Color.White
            )

            IconButton(onClick = { changeMonth(1) }) {
                Icon(Icons.Default.ArrowForward,
                    contentDescription = "Следующий месяц",
                    tint = Color.White
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(daysList.size) { index ->
                val (date, hasToDo) = daysList[index]
                DayItem(date = date,
                    selectedDate = selectedDate,
                    hasToDo = hasToDo
                ) {
                    onDateClick(date)
                }
            }
        }
    }
}