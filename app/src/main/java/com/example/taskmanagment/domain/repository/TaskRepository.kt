package com.example.taskmanagment.domain.repository

import com.example.taskmanagment.domain.model.Task

interface TaskRepository {

     suspend fun addTask(task:Task):Boolean
     suspend fun removeTask()
     suspend fun getTasks()

}