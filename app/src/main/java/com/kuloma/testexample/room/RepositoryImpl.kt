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

    override fun deleteToDo(entity: ToDoEntity) {
        db.getDao().deleteItem(entity)
    }

    override fun getAllToDo(): Flow<List<ToDoEntity>> = db.getDao().getAllItems()

}