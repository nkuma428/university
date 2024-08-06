package com.example.university.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.university.presentation.nav.NavGraph
import com.example.university.presentation.viewmodel.UniversityViewModel
import com.example.university.presentation.theme.UniversityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // ViewModel instance that is provided by Dagger-Hilt
    private val viewModel: UniversityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversityApp(viewModel)
        }
    }
}
@Composable
fun UniversityApp(viewModel : UniversityViewModel) {
    val navController = rememberNavController()
    MaterialTheme {
        Surface {
            NavGraph(navController = navController, viewModel = viewModel)
        }
    }
}