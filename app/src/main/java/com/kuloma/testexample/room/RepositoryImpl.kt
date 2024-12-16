package com.kuloma.testexample.room

import android.content.Context
import androidx.lifecycle.LiveData
import com.kuloma.testexample.domain.Repository
import com.kuloma.testexample.domain.ToDoEntity

class RepositoryImpl(context: Context): Repository {
    private val db = MainDb.getDb(context = context)

    override fun insertToDo(entity: ToDoEntity) {
        db.getDao().insertItem(entity)
    }

    override fun deleteToDoById(id: Int) {
        db.getDao().deleteItemById(id)
    }

    override fun getAllToDo(): LiveData<List<ToDoEntity>> = db.getDao().getAllItems()
}