package com.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Task(val id: Long, val title: String, val status: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TaskListScreen()
                }
            }
        }
    }
}

@Composable
fun TaskListScreen() {
    val sampleTasks = listOf(
        Task(1L, "Design Android App", "in-progress"),
        Task(2L, "Integrate with Micronaut", "pending"),
        Task(3L, "Write Unit Tests", "pending")
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("TaskFlow") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            items(sampleTasks) { task ->
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = task.title, style = MaterialTheme.typography.h6)
                        Text(text = "Status: ${task.status}", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskFlowTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            primary = androidx.compose.ui.graphics.Color(0xFF6200EE),
            primaryVariant = androidx.compose.ui.graphics.Color(0xFF3700B3),
            secondary = androidx.compose.ui.graphics.Color(0xFF03DAC5)
        ),
        content = content
    )
}
