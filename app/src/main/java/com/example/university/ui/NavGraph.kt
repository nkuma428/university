package com.example.university.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController, viewModel: UniversityViewModel) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") { UniversityListScreen(viewModel, navController) }
        //composable("details") { UniversityDetailScreen(viewModel) }
    }
}
