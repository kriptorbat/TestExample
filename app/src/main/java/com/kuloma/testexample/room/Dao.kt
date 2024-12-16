package com.kuloma.testexample.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kuloma.testexample.domain.ToDoEntity

@Dao
interface Dao {
    @Insert
    fun insertItem(entity: ToDoEntity)

    @Query("DELETE FROM toDo WHERE id = :id")
    fun deleteItemById(id: Int)

    @Query("SELECT * FROM toDo")
    fun getAllItems(): LiveData<List<ToDoEntity>>
}