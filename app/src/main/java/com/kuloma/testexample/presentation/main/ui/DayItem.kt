package com.kuloma.testexample.presentation.main.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kuloma.testexample.R
import com.kuloma.testexample.presentation.theme.Blue
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun DayItem(date: String, hasToDo: Boolean,selectedDate: String, onClick: () -> Unit) {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    val itemDate = LocalDate.parse(date, dateFormatter)
    val currentDate = LocalDate.now()

    val textColor = if (itemDate == currentDate) Blue else Color.Black
    val isSelected = date == selectedDate

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick()}
            .size(50.dp),
        shape = RoundedCornerShape(8.dp),
        border = if (isSelected) BorderStroke(2.dp, Blue) else null,
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .size(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(text = date.split(".")[2],
                style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )

            if (hasToDo) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_fiber_manual_record_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(10.dp)
                )
            }
        }
    }
}