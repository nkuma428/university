package com.example.university.presentation.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.university.presentation.viewmodel.UniversityViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityDetailScreen(viewModel: UniversityViewModel) {

    val university by viewModel.selectedUniversity.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("University Details") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Custom background color
                    titleContentColor = Color.White // Custom title color
                )
            )
        },
        content = { padding ->
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
