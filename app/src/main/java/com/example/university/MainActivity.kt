package com.example.university

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.university.ui.NavGraph
import com.example.university.ui.viewmodel.UniversityViewModel
import com.example.university.ui.theme.UniversityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    @Inject
//    lateinit var viewModel: UniversityViewModel
        //private lateinit var viewModel: UniversityViewModel

    private val viewModel: UniversityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversityApp(viewModel)
        }
        //viewModel = ViewModelProvider(this)[UniversityViewModel::class.java]
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