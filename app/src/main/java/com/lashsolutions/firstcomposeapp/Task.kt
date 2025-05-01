package com.lashsolutions.firstcomposeapp

data class Task(val title: String, val date: String, val time: String, val subTasks: List<SubTask>)
data class SubTask(val title: String, val completed: Boolean)

fun sampleTasks(): List<Task> {
    return listOf(
        Task("Task 2", "24/11/2021", "08:05 AM", listOf(
            SubTask("SubTask1", true),
            SubTask("SubTask2", false),
            SubTask("SubTask3", true),
        )),
        Task("Task 3", "29/11/2021", "02:30 PM", emptyList()),
        Task("Task 1", "24/11/2021", "12:33 AM", emptyList()),
        Task("Task 4", "", "", emptyList())
    )
}
