package com.kuloma.testexample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kuloma.testexample.domain.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(entity: ToDoEntity)

    @Query("DELETE FROM toDo WHERE id = :id")
    fun deleteItemById(id: Int)

    @Query("SELECT * FROM toDo")
    fun getAllItems(): Flow<List<ToDoEntity>>


    @Query("SELECT * FROM toDo WHERE day_date = :day")
    fun getAllItemsByDay(day: String): Flow<List<ToDoEntity>>
}