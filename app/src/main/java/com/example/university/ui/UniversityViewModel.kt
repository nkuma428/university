package com.example.university.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.data.remote.University
import com.example.university.domain.GetUniversitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase
) : ViewModel() {

    init {
        loadUniversities("United Arab Emirates")
    }

    private val _universities = mutableStateOf<List<University>>(emptyList())
    val universities: State<List<University>> get() = _universities

//    private val _isLoading = mutableStateOf(false)
//    val isLoading: State<Boolean> get() = _isLoading

    /*var universities = mutableStateOf<List<University>>(emptyList())
        private set*/

    var isLoading = mutableStateOf(false)
        private set

    var selectedUniversity = mutableStateOf<University?>(null)
        private set

    private fun loadUniversities(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.value = true
            _universities.value = getUniversitiesUseCase(country)
            isLoading.value = false
        }
    }
    fun selectUniversity(university: University) {
        selectedUniversity.value = university
    }
}