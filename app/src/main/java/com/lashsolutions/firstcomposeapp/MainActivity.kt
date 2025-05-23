package com.lashsolutions.firstcomposeapp

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lashsolutions.firstcomposeapp.ui.theme.FirstComposeAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeAppTheme {
                val navController = rememberNavController()
                val taskViewModel: TaskViewModel = viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF000000),
                                titleContentColor = White,
                                actionIconContentColor = White
                            ),
                            title = {
                                Text("To-Do App")
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        // Navigate to GridTask screen
                                        navController.navigate("grid_task")
                                    }
                                ) {
                                    Icon(Icons.Default.MoreVert, contentDescription = "More options")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "todo_home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("todo_home") {
                            ToDOHomePage(taskViewModel = taskViewModel)
                        }
                        composable("grid_task") {
                            GridTaskScreen(
                                onBackClick = {
                                    navController.popBackStack() // This navigates back to the previous screen
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun ToDOHomePage(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel = viewModel (),
) {
    var selectedChip by remember { mutableStateOf("All") }

    // Observe the filtered tasks
    val tasks = taskViewModel.filteredTasks

    // Update task filter when chip changes
    LaunchedEffect(selectedChip) {
        taskViewModel.applyFilter(selectedChip)
    }

    Surface(
        color = Color(0xFF121212),
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            FilterChips(
                selectedChip,
                onChipSelected = { selectedChip = it }
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(tasks) { task ->
                    TaskCard(task)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            AddTaskButton()
        }
    }
}

@Composable
fun FilterChips(
    selectedChip:String,
    onChipSelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        listOf("All", "Completed", "Important").forEachIndexed { index, label ->
            val backgroundColor = if( selectedChip==label) Color(0xFF3A57E8) else Color.Gray

            AssistChip(
                onClick = { onChipSelected(label) },
                label = { Text(label, color = White) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = backgroundColor
                )
            )
        }
    }
}

@Composable
fun TaskCard(task: Task) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(task.title, color = White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Edit, contentDescription = null, tint = White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Icon(painter = painterResource(R.drawable.calender_icon), contentDescription = null, tint = White)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(task.date, color = White)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(R.drawable.clock_icon), contentDescription = null, tint = White)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(task.time, color = White)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Star, contentDescription = null, tint = White)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(painter = painterResource(R.drawable.home_icon), contentDescription = null, tint = White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                task.description,
                color = White,

            )

        }
    }
}

@Composable
fun AddTaskButton() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(30.dp),
        ) {
            Text("+ Add Task", color = White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ToDOHomePagePreview() {
    FirstComposeAppTheme {
        ToDOHomePage()
    }
}