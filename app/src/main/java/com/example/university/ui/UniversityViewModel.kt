package com.example.university.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.data.remote.University
import com.example.university.domain.GetUniversitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase
) : ViewModel() {

    var universities = mutableStateOf<List<University>>(emptyList())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var selectedUniversity = mutableStateOf<University?>(null)
        private set

    fun loadUniversities(country: String) {
        viewModelScope.launch {
            isLoading.value = true
            universities.value = getUniversitiesUseCase(country)
            isLoading.value = false
        }
    }

    fun selectUniversity(university: University) {
        selectedUniversity.value = university
    }
}