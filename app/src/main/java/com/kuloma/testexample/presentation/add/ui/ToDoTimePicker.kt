package com.kuloma.testexample.presentation.add.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kuloma.testexample.presentation.add.AddViewModel
import com.kuloma.testexample.presentation.theme.VeryDarkBlue
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoTimePicker(
    dayDate: String,
    viewModel: AddViewModel,
    onTimeSelected: (String, String) -> Unit,
) {

    val currentTime = Calendar.getInstance()

    var showDialogStartTimeDialog by remember { mutableStateOf(false) }
    var showDialogEndTimeDialog by remember { mutableStateOf(false) }

    var startTimePickerState =
        rememberTimePickerState(
            initialHour = currentTime[Calendar.HOUR_OF_DAY],
            initialMinute = currentTime[Calendar.MINUTE],
            is24Hour = true
        )
    var endTimePickerState =
        rememberTimePickerState(
            initialHour = currentTime[Calendar.HOUR_OF_DAY],
            initialMinute = currentTime[Calendar.MINUTE],
            is24Hour = true
        )

    Card(colors = CardDefaults.cardColors(containerColor = VeryDarkBlue)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .background(color = VeryDarkBlue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Время начала ", color = Color.White)
            TimeText(
                hour = startTimePickerState.hour,
                minute = startTimePickerState.minute,
                onClickTime = {
                    showDialogStartTimeDialog = true
                }
            )
            Text(text = "Время конца ", color = Color.White)
            TimeText(
                hour = endTimePickerState.hour,
                minute = endTimePickerState.minute,
                onClickTime = {
                    showDialogEndTimeDialog = true
                }
            )
            if (showDialogStartTimeDialog) {
                TimePickerDialog(startTimePickerState, onClickOk = { time ->
                    startTimePickerState = time
                    showDialogStartTimeDialog = false
                })
            }
            if (showDialogEndTimeDialog) {
                TimePickerDialog(endTimePickerState, onClickOk = { time ->
                    endTimePickerState = time
                    showDialogEndTimeDialog = false
                })
            }

            LaunchedEffect(
                startTimePickerState.hour,
                startTimePickerState.minute,
                endTimePickerState.hour,
                endTimePickerState.minute
            ) {
                val startTimestamp = viewModel.getTimestamp(
                    dayDate,
                    startTimePickerState.hour,
                    startTimePickerState.minute
                )
                val endTimestamp = viewModel.getTimestamp(
                    dayDate,
                    endTimePickerState.hour,
                    endTimePickerState.minute
                )
                Log.d("writeData", "minute = ${endTimePickerState.minute}")
                onTimeSelected(startTimestamp, endTimestamp)
            }
        }
    }
}



