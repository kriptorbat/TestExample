package com.kuloma.testexample.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kuloma.testexample.ToDoEntity

@Dao
interface Dao {
    @Insert
    fun insertItem(entity: ToDoEntity)

    @Delete
    fun deleteItem(entity: ToDoEntity)

    @Query("SELECT * FROM toDo")
    fun getAllItem(): List<ToDoEntity>
}