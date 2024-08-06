package com.example.university.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.data.remote.University
import com.example.university.domain.GetUniversitiesUseCase
import com.example.university.util.AppConstants
import com.example.university.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase
) : ViewModel() {
    init {
        loadUniversities()
    }

    private val _universities = MutableStateFlow<UiState<List<University>>>(UiState.Loading)
    val universities: StateFlow<UiState<List<University>>> get() = _universities

    private val _selectedUniversity = MutableLiveData<University>()
    val selectedUniversity: LiveData<University> get() = _selectedUniversity
    fun loadUniversities() {
        viewModelScope.launch {
            val result = getUniversitiesUseCase(AppConstants.DEFAULT_COUNTRY_NAME)
            _universities.value = if (result.isSuccess) {
                UiState.Success(result.getOrNull() ?: emptyList())
            } else {
                UiState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }
    fun selectUniversity(university: University) {
        _selectedUniversity.value = university
    }
}