package com.kuloma.testexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toDo")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "date_start")
    val dateStart: String,

    @ColumnInfo(name = "date_finish")
    val dateFinish: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "day_date")
    val dayDate: String
)