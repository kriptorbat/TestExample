package com.kuloma.testexample.domain

import androidx.lifecycle.LiveData

interface Repository {
    fun insertToDo(entity: ToDoEntity)
    fun deleteToDoById(id: Int)
    fun getAllToDo(): LiveData<List<ToDoEntity>>
}