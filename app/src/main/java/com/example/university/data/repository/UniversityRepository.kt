package com.example.university.data.repository

import com.example.university.data.local.UniversityDao
import com.example.university.data.remote.ApiService
import com.example.university.data.remote.University
import com.example.university.util.NetworkUtil
import com.example.university.util.UniversityMapper
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val apiService: ApiService,
    private val networkUtil: NetworkUtil,
    private val universityDao: UniversityDao
) {
    suspend fun getUniversitiesByCountry(country: String): Result<List<University>> {
        return try {
            if (networkUtil.isNetworkAvailable()) {
                val universities = apiService.getUniversitiesByCountry(country)
                saveUniversitiesToLocal(universities)
                Result.success(universities)
            } else {
                val localUniversities = getUniversitiesFromLocal()
                if(localUniversities.isNotEmpty()) {
                    Result.success(localUniversities)
                } else {
                    Result.failure(Exception("No data found!"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun saveUniversitiesToLocal(universities: List<University>) {
        try {
            val entities = universities.map { UniversityMapper.toEntity(it) }
            // Insert all entities into the database
            universityDao.insertAll(entities)
        } catch (e: Exception) {
            println(e.message)
        }
    }
    private suspend fun getUniversitiesFromLocal(): List<University> {
        return try {
            val entities = universityDao.getAllUniversities()
            return entities.map { UniversityMapper.toUniversity(it) }
        } catch (e: Exception) {
            // Handle database exceptions here (e.g., SQLiteException)
            emptyList() // Return an empty list in case of error
        }
    }
}