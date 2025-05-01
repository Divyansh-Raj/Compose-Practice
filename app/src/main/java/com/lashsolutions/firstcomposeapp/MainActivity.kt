package com.lashsolutions.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.lashsolutions.firstcomposeapp.ui.theme.FirstComposeAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstComposeAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF000000),
                                titleContentColor = Color.White
                            ),
                            title = {
                                Text("To-Do App")
                            }
                        )
                    },
                ) { innerPadding ->
                    ToDOHomePage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ToDOHomePage(modifier: Modifier = Modifier) {
    val tasks = remember { sampleTasks() }

    Surface(
        color = Color(0xFF121212),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            FilterChips()

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
fun FilterChips() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        listOf("All", "Completed", "Important").forEachIndexed { index, label ->
            val backgroundColor = when (label) {
                "All" -> Color(0xFF3A57E8)
                else -> Color.Gray
            }

            AssistChip(
                onClick = {},
                label = { Text(label, color = Color.White) },
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
                Text(task.title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(task.date, color = Color.White)

                Spacer(modifier = Modifier.width(12.dp))

                Icon(Icons.Default.AccessTime, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(task.time, color = Color.White)

                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Star, contentDescription = null, tint = Color.White)
                Icon(Icons.Default.RadioButtonUnchecked, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            task.subTasks.forEach { sub ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = sub.completed,
                        onCheckedChange = {},
                        colors = CheckboxDefaults.colors(checkedColor = Color.White, uncheckedColor = Color.White)
                    )
                    Text(
                        text = sub.title,
                        color = Color.White,
                        style = if (sub.completed) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle.Default,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.White)
                }
            }
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
            Text("+ Add Task", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ToDOHomePagePreview() {
    FirstComposeAppTheme {
        ToDOHomePage("Android")
    }
}