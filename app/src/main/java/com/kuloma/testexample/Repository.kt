package com.kuloma.testexample

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun insertToDo(entity: ToDoEntity)
    fun deleteToDo(entity: ToDoEntity)
    fun getAllToDo(): Flow<List<ToDoEntity>>
    fun getAllToDoByDay(day: String): Flow<List<ToDoEntity>>
}