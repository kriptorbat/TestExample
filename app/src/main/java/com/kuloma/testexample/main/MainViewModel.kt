package com.kuloma.testexample.main

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel: ViewModel() {

//    var repository: Repository = RepositoryImpl(context = context)
//
//    fun addItem(entity: ToDoEntity){
//        repository.insertToDo(entity)
//    }

    fun formatTimestamp(unixTimestamp: Long): String {
        return try {
            val date = Date(unixTimestamp * 1000) // Умножаем на 1000 для преобразования секунд в миллисекунды
            val sdfOutput = SimpleDateFormat("HH:mm", Locale.getDefault())
            sdfOutput.format(date)
        } catch (e: Exception) {
            "Неверное время"
        }
    }

//    companion object{
//        val Factory: ViewModelProvider.Factory = viewModelFactory{
//            initializer {
//                val context = this[APPLICATION_KEY] as Context
//                MainViewModel(context)
//            }
//        }
//    }
}