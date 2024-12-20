package com.kuloma.testexample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kuloma.testexample.domain.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 5)
abstract class MainDb: RoomDatabase() {
    abstract fun getDao(): Dao
    companion object{
        fun getDb(context: Context): MainDb{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "todo.db").build()
        }
    }
}