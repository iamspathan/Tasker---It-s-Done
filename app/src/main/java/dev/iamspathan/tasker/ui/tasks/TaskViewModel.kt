package dev.iamspathan.tasker.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.iamspathan.tasker.data.TaskDao

class TaskViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
): ViewModel() {

    val tasks  = taskDao.getTask().asLiveData()

}