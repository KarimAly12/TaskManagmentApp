package com.example.taskmanagment.domain.model

data class Task(
    val name:String,
    val description:String,
    val date:String,
    val subtasks: MutableList<String>
)