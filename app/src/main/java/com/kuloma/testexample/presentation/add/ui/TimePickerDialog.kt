package com.kuloma.testexample.presentation.add.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kuloma.testexample.presentation.theme.DarkBlue
import com.kuloma.testexample.presentation.theme.VeryDarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(timePickerState: TimePickerState, onClickOk: (TimePickerState) -> Unit) {
    AlertDialog(
        containerColor = VeryDarkBlue,
        onDismissRequest = {},
        confirmButton = {
            TextButton(
                onClick = { onClickOk(timePickerState) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = DarkBlue
                )
            ) {
                Text("OK")
            }
        },
        text = {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    clockDialColor = DarkBlue,
                    selectorColor = VeryDarkBlue,
                    containerColor = DarkBlue,
                    periodSelectorBorderColor = DarkBlue,
                    clockDialSelectedContentColor = Color.White,
                    clockDialUnselectedContentColor = Color.White,
                    periodSelectorSelectedContainerColor = Color.White,
                    periodSelectorUnselectedContainerColor = Color.White,
                    periodSelectorSelectedContentColor = Color.White,
                    periodSelectorUnselectedContentColor = Color.White,
                    timeSelectorSelectedContainerColor = DarkBlue,
                    timeSelectorUnselectedContainerColor = DarkBlue,
                    timeSelectorSelectedContentColor = Color.White,
                    timeSelectorUnselectedContentColor = Color.White
                )
            )
        }
    )
}