package com.example.petminder.feature.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petminder.feature.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    vm: TaskViewModel,
    taskId: String? = null,
    onDone: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Edit Task", style = MaterialTheme.typography.headlineSmall)

            OutlinedTextField(
                value = vm.getTaskTitle(taskId),
                onValueChange = { vm.updateTaskTitle(taskId, it) },
                label = { Text("Task title") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = { vm.saveTask(taskId); onDone() }) {
                Text("Save")
            }
        }
    }
}
