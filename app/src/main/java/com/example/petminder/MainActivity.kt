package com.example.petminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petminder.feature.viewmodel.TaskViewModel
import com.example.petminder.navigation.AppNav
import com.example.petminder.ui.PetMinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetMinderTheme {
                val vm: TaskViewModel = viewModel()
                AppNav(vm)
            }
        }
    }
}
