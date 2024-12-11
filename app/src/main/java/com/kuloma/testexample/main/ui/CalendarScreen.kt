package com.kuloma.testexample.main.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.kuloma.testexample.R
import com.kuloma.testexample.main.MainViewModel
import java.util.Calendar

@Composable
fun CalendarScreen(
    onDateClick: (String) -> Unit,
    datesWithToDo: Set<String>,
) {
    val calendars: ArrayList<CalendarDay> = ArrayList()

    for (dateString in datesWithToDo) {
        // Разбираем строку даты
        val parts = dateString.split(".")

        val day = parts[2].toIntOrNull() ?: continue
        val month = parts[1].toIntOrNull()?.minus(1) ?: continue
        val year = parts[0].toIntOrNull() ?: continue

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        val calendarDay = CalendarDay(calendar)
        calendarDay.imageResource = R.drawable.baseline_fiber_manual_record_24

        calendars.add(calendarDay)

    }


//    val calendar = Calendar.getInstance()
//    calendar.set(2024, 11, 20)
//    val calendarDay = CalendarDay(calendar)
//    calendarDay.imageResource = R.drawable.baseline_fiber_manual_record_24

//    calendars.add(calendarDay)


    AndroidView(factory = { CalendarView(it) }, update = {

        it.setCalendarDays(calendars)
        it.setHeaderColor(R.color.veryDarkBlue)

        it.setOnCalendarDayClickListener(
            object : OnCalendarDayClickListener {
                override fun onClick(calendarDay: CalendarDay) {
                    val date = calendarDay.calendar.get(Calendar.YEAR).toString() + "." +
                            (calendarDay.calendar.get(Calendar.MONTH) + 1).toString() + "." +
                            calendarDay.calendar.get(Calendar.DAY_OF_MONTH).toString()
                    Log.d("date", date)
                    onDateClick(date)
                }
            })
    })
}