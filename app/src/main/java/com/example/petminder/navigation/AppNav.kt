package com.example.petminder.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petminder.feature.edit.EditTaskScreen
import com.example.petminder.feature.viewmodel.TaskViewModel

@Composable
fun AppNav(vm: TaskViewModel) {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "editTask",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("editTask") {
                EditTaskScreen(vm = vm, taskId = null, onDone = { navController.popBackStack() })
            }
        }
    }
}
