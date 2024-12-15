package com.kuloma.testexample.presentation.info

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InfoViewModel : ViewModel() {

    fun formatTimestamp(unixTimestamp: Long): String {
        return if (unixTimestamp < 0) {
            "Неверное время"
        } else {
            try {
                val date = Date(unixTimestamp * 1000)
                val sdfOutput = SimpleDateFormat("HH:mm", Locale.getDefault())
                sdfOutput.format(date)
            } catch (e: Exception) {
                "Неверное время"
            }
        }
    }
}