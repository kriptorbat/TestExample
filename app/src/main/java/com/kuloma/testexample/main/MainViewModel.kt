package com.kuloma.testexample.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kuloma.testexample.Repository
import com.kuloma.testexample.ToDoEntity
import com.kuloma.testexample.room.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel(context: Context): ViewModel() {

    var repository: Repository = RepositoryImpl(context = context)

    fun addItem(entity: ToDoEntity){
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertToDo(entity)
        }
    }
    fun getAllItemByDate(day: String): Flow<List<ToDoEntity>>{
        return repository.getAllToDoByDay(day)
    }

    fun getAllItem(): Flow<List<ToDoEntity>>{
        return repository.getAllToDo()
    }

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
                MainViewModel(context)
            }
        }
    }
}