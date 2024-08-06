package com.example.university.domain

import com.example.university.data.repository.UniversityRepository
import com.example.university.data.remote.University
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(private val repository: UniversityRepository) {
    suspend operator fun invoke(country: String): List<University> {
        return repository.getUniversitiesByCountry(country)
    }
}
