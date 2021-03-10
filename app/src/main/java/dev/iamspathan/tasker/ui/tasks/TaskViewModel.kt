package dev.iamspathan.tasker.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.iamspathan.tasker.data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class TaskViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
): ViewModel() {


    val searchQuery = MutableStateFlow("")

    private val taskFlow= searchQuery.flatMapLatest {
        taskDao.getTask(it)
    }

    val tasks  = taskFlow.asLiveData()

}