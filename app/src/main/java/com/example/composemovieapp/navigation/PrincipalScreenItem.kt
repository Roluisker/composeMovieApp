package com.example.composemovieapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class PrincipalScreenItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
