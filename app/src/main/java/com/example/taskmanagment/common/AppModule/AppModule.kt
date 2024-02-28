package com.example.taskmanagment.common.AppModule

import com.example.taskmanagment.data.repository.TaskRepositoryImpl
import com.example.taskmanagment.domain.repository.TaskRepository
import com.example.taskmanagment.domain.usecase.AddTask
import com.example.taskmanagment.domain.usecase.TaskUsecases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskRepository():TaskRepository{
        return TaskRepositoryImpl()

    }

    @Provides
    @Singleton
    fun provideTaskUsecases(taskRepository: TaskRepository): TaskUsecases {
        return TaskUsecases(
            addTask = AddTask(taskRepository)
        )

    }
}