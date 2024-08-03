package com.example.university.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.university.data.remote.University

@Composable
fun UniversityListScreen(viewModel: UniversityViewModel, navController: NavController) {
    val state = viewModel.universities.value
    //val isLoading by viewModel.isLoading.observeAsState(false)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        /*if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(state) { university ->
                    UniversityListItem(university) {
                        viewModel.selectUniversity(university)
                        navController.navigate("details")
                    }
                }
            }
        }*/

        LazyColumn {
            items(state) { university ->
                UniversityListItem(university) {
                    viewModel.selectUniversity(university)
                    navController.navigate("details")
                }
            }
        }
    }
}

@Composable
fun UniversityListItem(university: University, onClick: () -> Unit) {
    Card(modifier = Modifier.padding(vertical = 4.dp).clickable { onClick() }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = university.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = university.country, style = MaterialTheme.typography.headlineSmall)
        }
    }
}