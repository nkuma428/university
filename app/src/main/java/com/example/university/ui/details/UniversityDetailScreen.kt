package com.example.university.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.university.ui.UniversityViewModel

@Composable
fun UniversityDetailScreen(viewModel: UniversityViewModel) {
    val university by viewModel.selectedUniversity.observeAsState()
    university?.let {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = it.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = it.country, style = MaterialTheme.typography.headlineSmall)
            Text(text = it.alpha_two_code, style = MaterialTheme.typography.bodySmall)
        }
    }
}
