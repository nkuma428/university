package com.example.university.ui.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.university.ui.viewmodel.UniversityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityListScreen(viewModel: UniversityViewModel, navController: NavController) {

    val state = viewModel.universities.value
    val isLoading = viewModel.isLoading.collectAsState(true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("University List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Custom background color
                    titleContentColor = Color.White // Custom title color
                )
            )
        },
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {
                if (isLoading.value) {
                    // CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    LazyColumn(modifier = Modifier.padding(10.dp)) {
                        items(state) { university ->
                            UniversityListItem(university) {
                                viewModel.selectUniversity(university)
                                navController.navigate("details")
                            }
                        }
                    }
                }
            }
        }
    )
}