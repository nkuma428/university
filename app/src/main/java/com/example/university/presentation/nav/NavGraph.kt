package com.example.university.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.university.presentation.details.UniversityDetailScreen
import com.example.university.presentation.listing.UniversityListScreen
import com.example.university.presentation.viewmodel.UniversityViewModel

@Composable
fun NavGraph(navController: NavHostController, viewModel: UniversityViewModel) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") { UniversityListScreen(viewModel, navController) }
        composable("details") { UniversityDetailScreen(viewModel) }
    }
}
