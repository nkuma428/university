package com.example.university.presentation.listing

import android.net.Uri
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
import com.example.university.util.UiState
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityListScreen(viewModel: UniversityViewModel, navController: NavController) {

    // Collect the current state of universities from the ViewModel
    val uiState = viewModel.universities.collectAsState()

    // Observe the refresh trigger
    val refreshFlag = navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(AppConstants.KEY_REFRESH)?.observeAsState()

    // Trigger a refresh if the refresh flag is set to true
    if (refreshFlag?.value == true) {
        viewModel.loadUniversities()
        // Clear the refresh trigger to avoid repeated refreshes
        navController.currentBackStackEntry?.savedStateHandle?.set(AppConstants.KEY_REFRESH, false)
    }

    Scaffold(
        topBar = {
            // TopAppBar with custom colors and title
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_university_list)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Custom background color
                    titleContentColor = Color.White // Custom title color
                )
            )
        },
        content = { padding ->
            // Handling different states of the UI: Loading, Success, and Error
            when (val state = uiState.value) {
                is UiState.Loading -> {
                    // Display a loading indicator in the center of the screen
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Success -> {
                    // Display the list of universities
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            // Render each university as a list item
                            items(state.data) { university ->
                                UniversityListItem(university) {
                                    // Handle click event to navigate to the detail screen
                                    //viewModel.selectUniversity(university)
                                    val universityJson = Uri.encode(Gson().toJson(university))
                                    navController.navigate("${AppConstants.ROUTE_DETAIL_SCREEN}/$universityJson")
                                }
                            }
                        }
                    }
                }
                is UiState.Error -> {
                    // Display an error message in the center of the screen
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = state.message)
                    }
                }
            }
        }
    )
}