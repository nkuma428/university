package com.example.university.data


import com.example.university.data.local.UniversityDao
import com.example.university.data.local.UniversityEntity
import com.example.university.data.remote.ApiService
import com.example.university.data.remote.University
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val apiService: ApiService,
    private val universityDao: UniversityDao
) {
    suspend fun getUniversitiesByCountry(country: String): List<University> {
        return if (isInternetAvailable()) {
            val universities = apiService.getUniversitiesByCountry(country)
            saveUniversitiesToLocal(universities)
            universities
        } else {
            getUniversitiesFromLocal()
        }
    }

    private suspend fun saveUniversitiesToLocal(universities: List<University>) {
        val entities = universities.map {
            UniversityEntity(
                name = it.name,
                country = it.country,
                alpha_two_code = it.alpha_two_code,
                web_pages = it.web_pages
            )
        }
        universityDao.insertAll(entities)
    }

    private suspend fun getUniversitiesFromLocal(): List<University> {
        return universityDao.getAllUniversities().map {
            University(
                name = it.name,
                country = it.country,
                alpha_two_code = it.alpha_two_code,
                web_pages = it.web_pages
            )
        }
    }

    private fun isInternetAvailable(): Boolean {
        // Implement actual network check
        return true
    }
}