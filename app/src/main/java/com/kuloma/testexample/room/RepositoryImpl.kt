package com.kuloma.testexample.room

import android.content.Context
import com.kuloma.testexample.Repository
import com.kuloma.testexample.ToDoEntity
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(context: Context): Repository {
    private val db = MainDb.getDb(context = context)

    override fun insertToDo(entity: ToDoEntity) {
        db.getDao().insertItem(entity)
    }

    override fun deleteToDoById(id: Int) {
        db.getDao().deleteItemById(id)
    }

    override fun getAllToDo(): Flow<List<ToDoEntity>> = db.getDao().getAllItems()

    override fun getAllToDoByDay(day: String): Flow<List<ToDoEntity>> = db.getDao().getAllItemsByDay(day)

}