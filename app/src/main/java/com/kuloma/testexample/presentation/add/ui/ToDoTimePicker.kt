package com.kuloma.testexample.presentation.add.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kuloma.testexample.presentation.add.AddViewModel
import com.kuloma.testexample.presentation.theme.VeryDarkBlue
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoTimePicker(dayDate: String, viewModel: AddViewModel, onTimeSelected: (String, String) -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        val currentTime = Calendar.getInstance()

        val startTimePickerState =
            rememberTimePickerState(
                initialHour = currentTime[Calendar.HOUR_OF_DAY],
                initialMinute = currentTime[Calendar.MINUTE],
                is24Hour = true
            )
        val endTimePickerState =
            rememberTimePickerState(
                initialHour = currentTime[Calendar.HOUR_OF_DAY],
                initialMinute = currentTime[Calendar.MINUTE],
                is24Hour = true
            )

        Card {
            Column (
                modifier = Modifier.fillMaxWidth()
                    .background(color = VeryDarkBlue),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Время начала ", color = Color.White)
                TimeInput(
                    state = startTimePickerState
                )
                Text(text = "Время конца ", color = Color.White)
                TimeInput(
                    state = endTimePickerState
                )
                LaunchedEffect(startTimePickerState.hour, startTimePickerState.minute, endTimePickerState.hour, endTimePickerState.minute) {
                    val startTimestamp = viewModel.getTimestamp(dayDate, startTimePickerState.hour, startTimePickerState.minute)
                    val endTimestamp = viewModel.getTimestamp(dayDate, endTimePickerState.hour, endTimePickerState.minute)
                    Log.d("writeData","minute = ${endTimePickerState.minute}")
                    onTimeSelected(startTimestamp, endTimestamp)
                }
            }
        }
    }
}