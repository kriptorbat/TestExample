package com.kuloma.testexample.presentation.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kuloma.testexample.domain.Repository
import com.kuloma.testexample.domain.ToDoEntity
import com.kuloma.testexample.room.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel(private val repository: Repository): ViewModel() {

    fun addItem(entity: ToDoEntity){
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertToDo(entity)
        }
    }

    fun getAllItem(): LiveData<List<ToDoEntity>>{
        return repository.getAllToDo()
    }
    fun deleteToDo(id: Int){
        CoroutineScope(Dispatchers.IO).launch{
            repository.deleteToDoById(id)
        }
    }

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

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val context = this[APPLICATION_KEY] as Context
                val repository: Repository = RepositoryImpl(context)
                MainViewModel(repository)
            }
        }
    }
}