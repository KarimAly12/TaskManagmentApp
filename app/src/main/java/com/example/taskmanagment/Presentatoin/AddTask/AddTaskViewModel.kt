package com.example.taskmanagment.Presentatoin.AddTask

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagment.domain.model.Task
import com.example.taskmanagment.domain.usecase.TaskUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskUsecases: TaskUsecases
): ViewModel() {

    val name = mutableStateOf("")
    val description = mutableStateOf("")
    val subtaskValue = mutableStateOf("")
    val date = mutableStateOf("")



    //al subtasksState = MutableStateFlow(SubtasksUIState())
    //val subtasksState = _subtasksState.asStateFlow()

    val subtasks = mutableStateListOf<String>()



    fun addSubtask(){
        subtasks.add(subtaskValue.value)

    }

    fun addTask(task: Task){
        viewModelScope.launch {
            taskUsecases.addTask(task)
        }
    }

    fun onSwipeToDelete(subtaskValue:String){
//        _subtasksState.update {
//            it.copy(
//                subtasks =  it.subtasks.filterNot {subtask ->
//                    subtask == subtaskValue
//                }
//            )
//        }

        Log.i("test", subtaskValue)
        subtasks.remove(subtaskValue)


    }
}