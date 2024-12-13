package com.kuloma.testexample.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun insertToDo(entity: ToDoEntity)
    fun deleteToDoById(id: Int)
    fun getAllToDo(): Flow<List<ToDoEntity>>
    fun getAllToDoByDay(day: String): Flow<List<ToDoEntity>>
}