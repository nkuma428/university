package com.example.university

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.university.ui.NavGraph
import com.example.university.ui.UniversityViewModel
import com.example.university.ui.theme.UniversityTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    @Inject
//    lateinit var viewModel: UniversityViewModel
        private lateinit var viewModel: UniversityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversityApp(viewModel)
        }
        viewModel = ViewModelProvider(this)[UniversityViewModel::class.java]
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
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UniversityTheme {
        //UniversityApp(UniversityViewModel())
    }
}