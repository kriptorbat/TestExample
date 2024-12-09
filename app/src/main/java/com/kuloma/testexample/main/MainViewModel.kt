package com.kuloma.testexample.main

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel: ViewModel() {
    fun formatTimestamp(unixTimestamp: Long): String {
        return try {
            val date = Date(unixTimestamp * 1000) // Умножаем на 1000 для преобразования секунд в миллисекунды
            val sdfOutput = SimpleDateFormat("HH:mm", Locale.getDefault())
            sdfOutput.format(date)
        } catch (e: Exception) {
            "Неверное время"
        }
    }
}