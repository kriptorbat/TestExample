package com.kuloma.testexample.info

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InfoViewModel(context: Context) : ViewModel() {
    fun formatTimestamp(unixTimestamp: Long): String {
        return try {
            val date = Date(unixTimestamp * 1000) // Умножаем на 1000 для преобразования секунд в миллисекунды
            val sdfOutput = SimpleDateFormat("HH:mm", Locale.getDefault())
            sdfOutput.format(date)
        } catch (e: Exception) {
            "Неверное время"
        }
    }
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val context = this[APPLICATION_KEY] as Context
                InfoViewModel(context)
            }
        }
    }
}