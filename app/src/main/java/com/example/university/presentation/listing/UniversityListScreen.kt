package com.example.university.presentation.listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.university.R
import com.example.university.presentation.viewmodel.UniversityViewModel
import com.example.university.util.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityListScreen(viewModel: UniversityViewModel, navController: NavController) {

    val universities = viewModel.universities.value
    val isLoading = viewModel.isLoading.collectAsState(true)

    // Observe the refresh trigger
    val refreshFlag = navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(AppConstants.KEY_REFRESH)?.observeAsState()

    // Trigger a refresh if needed
    if (refreshFlag?.value == true) {
        viewModel.loadUniversities()
        // Clear the refresh trigger
        navController.currentBackStackEntry?.savedStateHandle?.set(AppConstants.KEY_REFRESH, false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_university_list)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Custom background color
                    titleContentColor = Color.White // Custom title color
                )
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyColumn(modifier = Modifier.padding(10.dp)) {
                        items(universities) { university ->
                            UniversityListItem(university) {
                                viewModel.selectUniversity(university)
                                navController.navigate(AppConstants.ROUTE_DETAIL_SCREEN)
                            }
                        }
                    }
                }
            }
        }
    )
}