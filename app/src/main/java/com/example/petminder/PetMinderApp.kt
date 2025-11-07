package com.example.petminder

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomItem(val label: String, val icon: ImageVector)

private val bottomItems = listOf(
    BottomItem("Tasks", Icons.Filled.CalendarMonth),
    BottomItem("Pets", Icons.Filled.Pets),
    BottomItem("Profile", Icons.Filled.SupervisorAccount)
)

@Composable
fun PetMinderApp() {
    NavigationBar {
        bottomItems.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
