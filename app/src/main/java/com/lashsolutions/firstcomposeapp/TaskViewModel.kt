package com.lashsolutions.firstcomposeapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    //Mutable list of tasks
    private val _tasks = mutableStateListOf<Task>().apply {
        addAll(sampleTasks())
    }
    //public view of the above tasks list
    val allTasks:List<Task> get() = _tasks

    // State for filtered task list
    var filteredTasks by mutableStateOf<List<Task>>(_tasks)
        private set

    private var currentFilter: String = "All"

    //function to add the new tasks
    fun addTask(task:Task){
        _tasks.add(task)
        applyFilter(currentFilter) // re-apply current filter when task added
    }

    // Filter tasks based on selected chip
    fun applyFilter(label: String) {
        currentFilter = label
        filteredTasks = when (label) {
            "Completed" -> _tasks.filter { it.isCompleted }
            "Important" -> _tasks.filter { it.isImportant }
            else -> _tasks // "All"
        }
    }
}