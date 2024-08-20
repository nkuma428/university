package com.example.university.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.university.data.remote.University
import com.example.university.presentation.details.UniversityDetailScreen
import com.example.university.presentation.listing.UniversityListScreen
import com.example.university.presentation.viewmodel.UniversityViewModel
import com.example.university.util.AppConstants
import com.google.gson.Gson

@Composable
fun NavGraph(navController: NavHostController, viewModel: UniversityViewModel) {
    /*NavHost(navController = navController, startDestination = AppConstants.ROUTE_LIST_SCREEN) {
        composable(AppConstants.ROUTE_LIST_SCREEN) { UniversityListScreen(viewModel, navController) }
        composable(AppConstants.ROUTE_DETAIL_SCREEN) { UniversityDetailScreen(navController, viewModel) }
    }*/
    NavHost(navController = navController, startDestination = AppConstants.ROUTE_LIST_SCREEN) {
        composable(AppConstants.ROUTE_LIST_SCREEN) { UniversityListScreen(viewModel, navController) }
        composable(
            route = "${AppConstants.ROUTE_DETAIL_SCREEN}/{university}",
            arguments = listOf(navArgument("university") { type = NavType.StringType })
        ) { backStackEntry ->
            val universityJson = backStackEntry.arguments?.getString("university")
            val university = Gson().fromJson(universityJson, University::class.java)
            UniversityDetailScreen(navController, university)
        }
    }
}
