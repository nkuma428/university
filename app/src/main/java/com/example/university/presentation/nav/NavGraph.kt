package com.example.university.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.university.presentation.details.UniversityDetailScreen
import com.example.university.presentation.listing.UniversityListScreen
import com.example.university.presentation.viewmodel.UniversityViewModel
import com.example.university.util.AppConstants

@Composable
fun NavGraph(navController: NavHostController, viewModel: UniversityViewModel) {
    NavHost(navController = navController, startDestination = AppConstants.ROUTE_LIST_SCREEN) {
        composable(AppConstants.ROUTE_LIST_SCREEN) { UniversityListScreen(viewModel, navController) }
        composable(AppConstants.ROUTE_DETAIL_SCREEN) { UniversityDetailScreen(navController, viewModel) }
    }
}
