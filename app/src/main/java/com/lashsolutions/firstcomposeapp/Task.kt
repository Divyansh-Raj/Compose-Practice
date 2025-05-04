package com.lashsolutions.firstcomposeapp

data class Task(
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val isImportant: Boolean,
    val isCompleted: Boolean
)

fun sampleTasks(): List<Task> {
    return listOf(
        Task("Task 1", "24/11/2021", "08:05 AM", "Complete the information for Genpact Application", isImportant = true, isCompleted = false),
        Task("Task 2", "29/11/2021", "02:30 PM", "Prepare for interview", isImportant = true, isCompleted = true),
        Task("Task 3", "24/11/2021", "12:33 AM", "Buy groceries", isImportant = false, isCompleted = false),
        Task("Task 4", "", "", "Empty task for placeholder testing", isImportant = false, isCompleted = false),
        Task("Task 5", "01/12/2021", "11:00 AM", "Submit college assignment", isImportant = true, isCompleted = false),
        Task("Task 6", "02/12/2021", "05:45 PM", "Go for a run", isImportant = false, isCompleted = true),
        Task("Task 7", "03/12/2021", "09:30 AM", "Book flight tickets", isImportant = true, isCompleted = true),
        Task("Task 8", "04/12/2021", "07:15 PM", "Watch Compose tutorial", isImportant = false, isCompleted = false)
    )
}
