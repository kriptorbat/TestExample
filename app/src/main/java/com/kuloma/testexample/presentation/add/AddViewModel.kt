package com.kuloma.testexample.presentation.add

import android.util.Log
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class AddViewModel: ViewModel() {
    fun getTimestamp(dayDate: String, hour: Int, minute: Int): String {
        Log.d("getTimestamp", "dayDate=$dayDate hour=$hour minute=$minute")
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        val dateString = "$dayDate $hour:$minute"
        return (dateFormat.parse(dateString)?.time?.div(1000))?.toString() ?: "0"
    }
}