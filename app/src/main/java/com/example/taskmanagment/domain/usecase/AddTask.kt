package com.example.taskmanagment.domain.usecase

import com.example.taskmanagment.domain.model.Task
import com.example.taskmanagment.domain.repository.TaskRepository

class AddTask(private val subtaskRepository: TaskRepository) {

    suspend operator fun invoke(task: Task):Boolean{
        return subtaskRepository.addTask(task)
    }
}