package com.kuloma.testexample

data class ToDoEntity(
    val id: Int? = null,
    val date_start: String,
    val date_finifsh: String,
    val name: String,
    val description: String
)