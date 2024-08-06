package com.example.university.data.repository

import com.example.university.data.local.UniversityDao
import com.example.university.data.remote.ApiService
import com.example.university.data.remote.University
import com.example.university.util.NetworkUtil
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val apiService: ApiService,
    private val networkUtil: NetworkUtil,
    private val universityDao: UniversityDao
) {
    suspend fun getUniversitiesByCountry(country: String): List<University> {
        return if (networkUtil.isNetworkAvailable()) {
            val universities = apiService.getUniversitiesByCountry(country)
            saveUniversitiesToLocal(universities)
            universities
        } else {
            getUniversitiesFromLocal()
        }
    }

    private suspend fun saveUniversitiesToLocal(universities: List<University>) {
        universityDao.insertAll(universities)
    }
    private suspend fun getUniversitiesFromLocal(): List<University> {
        return return universityDao.getAllUniversities()
    }
}