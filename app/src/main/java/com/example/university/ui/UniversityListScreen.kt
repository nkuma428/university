package com.example.university.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.university.data.remote.University


@Composable
fun UniversityListScreen(viewModel: UniversityViewModel, navController: NavController) {
    val state = viewModel.universities.value
    //val isLoading by viewModel.isLoading.collectAsState(false)
    //val isLoading = viewModel.isLoading.value
    val isLoading = viewModel.isLoading.collectAsState(true)

    Log.e("===UniversityListScreen", "isLoading==$isLoading")



    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (isLoading.value) {
            Log.e("===UniversityListScreen", "isLoading")
            //CircularProgressIndicator()
        } else {
            Log.e("===UniversityListScreen", "isLoading false")
            LazyColumn {
                items(state) { university ->
                    UniversityListItem(university) {
                        viewModel.selectUniversity(university)
                        navController.navigate("details")
                        //navController.navigate("details/${university.name}")
                    }
                }
            }
        }

        /*LazyColumn {
            items(state) { university ->
                UniversityListItem(university) {
                    viewModel.selectUniversity(university)
                    navController.navigate("details")
                }
            }
        }*/
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