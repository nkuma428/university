package com.example.university.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.data.remote.University
import com.example.university.domain.GetUniversitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading get() = _isLoading

    /*var universities = mutableStateOf<List<University>>(emptyList())
        private set*/

    /*var isLoading = mutableStateOf(false)
        private set*/

    /*var selectedUniversity = MutableStateFlow<University?>(null)
        private set*/

    private val _selectedUniversity = MutableLiveData<University>()
    val selectedUniversity: LiveData<University> get() = _selectedUniversity

    private fun loadUniversities(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _universities.value = getUniversitiesUseCase(country)
            _isLoading.value = false
        }
    }
    fun selectUniversity(university: University) {
        _selectedUniversity.value = university
    }
}