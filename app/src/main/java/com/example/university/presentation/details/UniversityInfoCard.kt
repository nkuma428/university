package com.example.university.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.university.R

@Composable
fun UniversityInfoCard(
    universityName: String?,
    universityState: String?,
    country: String?,
    countryCode: String?,
    webPageUrl: String?,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = universityName ?: stringResource(id = R.string.not_available),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer (modifier = Modifier.height(8.dp))
        Text(
            text = universityState ?: stringResource(id = R.string.not_available),
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = country ?: stringResource(id = R.string.not_available),
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = countryCode ?: stringResource(id = R.string.not_available),
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
        webPageUrl?.let {
            Surface(
                modifier = modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                WebPageScreen(it)
            }
        }
    }
}

