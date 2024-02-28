package com.example.taskmanagment.data.repository

import com.example.taskmanagment.domain.model.Task
import com.example.taskmanagment.domain.repository.TaskRepository
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.database
import java.lang.Exception

class TaskRepositoryImpl: TaskRepository {


    val database = Firebase.database

    override suspend fun addTask(task:Task):Boolean {


        try {
            val ref = database.getReference("Tasks")

            ref.child(task.name).setValue(task)
        }catch (e: Exception){

            return false

        }



        return true
    }

    override suspend fun removeTask() {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks() {
        TODO("Not yet implemented")
    }
}