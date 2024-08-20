package com.example.university.presentation.details

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.university.R
import com.example.university.data.remote.University
import com.example.university.presentation.viewmodel.UniversityViewModel
import com.example.university.util.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityDetailScreen(navController: NavHostController, university: University) {

    // Observe the selected university from the ViewModel
    //val university by viewModel.selectedUniversity.observeAsState()

    Scaffold(
        topBar = {
            // TopAppBar for the screen
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_university_details)) },
                actions = {
                    IconButton(onClick = {
                        // Navigate back and refresh the UniversityListScreen
                        navController.previousBackStackEntry?.savedStateHandle?.set(AppConstants.KEY_REFRESH, true)
                        navController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_refresh),
                            contentDescription = "Refresh",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Custom background color
                    titleContentColor = Color.White // Custom title color
                )
            )
        },
        content = { padding ->
            // Display the UniversityInfoCard if a university is selected
            university?.let {
                UniversityInfoCard(
                    universityName = it.name,
                    universityState = it.stateProvince,
                    country = it.country,
                    countryCode = it.alphaTwoCode,
                    webPageUrl = it.webPages.firstOrNull(),
                    modifier = Modifier.padding(padding)
                )
            }
        }
    )
}
