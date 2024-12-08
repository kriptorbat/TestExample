package com.kuloma.testexample

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.kuloma.testexample.ui.theme.TestExampleTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestExampleTheme {
                CalendarScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestExampleTheme {
        CalendarScreen()
    }
}
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//app:abbreviationsBarColor="@color/veryDarkBlue"
//app:abbreviationsLabelsColor="@color/whiteGray"
//app:anotherMonthsDaysLabelsColor="@color/darkBlue"
//app:daysLabelsColor="@color/white"
//app:headerColor="@color/darkBlue"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toTopOf="parent"
//app:pagesColor="@color/veryDarkBlue"
//app:todayLabelColor="@color/whitePurple" />

@Composable
fun CalendarScreen() {
    var date by remember {
        mutableStateOf("")
    }

    //здесь будет вызов функции которая возвращает список с днями в которых есть события
    val calendars: ArrayList<CalendarDay> = ArrayList()
    val calendar = Calendar.getInstance()
    calendar.set(2024,11, 20)
    val calendarDay = CalendarDay(calendar)
    calendarDay.imageResource = R.drawable.baseline_fiber_manual_record_24

    calendars.add(calendarDay)

    Column {
        AndroidView(factory = { CalendarView(it) }, update = {

            it.setCalendarDays(calendars)
            it.setHeaderColor(R.color.veryDarkBlue)

            it.setOnCalendarDayClickListener(
                object: OnCalendarDayClickListener {
                    override fun onClick(calendarDay: CalendarDay) {
//                        Calendar.MONTH)+1
                        date = calendarDay.calendar.get(Calendar.DAY_OF_MONTH).toString()
                    }
                })
        })

        Text(text = date)
    }
}

