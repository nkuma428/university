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
    // Function to fetch universities by country, either from the network or local database
    suspend fun getUniversitiesByCountry(country: String): Result<List<University>> {
        return try {
            if (networkUtil.isNetworkAvailable()) {
                // Fetch universities from the network
                val universities = apiService.getUniversitiesByCountry(country)
                // Save the fetched universities to the local database
                saveUniversitiesToLocal(universities)
                // Return the fetched universities wrapped in a success result
                Result.success(universities)
            } else {
                // Fetch universities from the local database
                val localUniversities = getUniversitiesFromLocal()
                if(localUniversities.isNotEmpty()) {
                    // Return the local universities if success result if not empty
                    Result.success(localUniversities)
                } else {
                    // Return a failure result with an exception if no local data found
                    Result.failure(Exception("No data found!"))
                }
            }
        } catch (e: Exception) {
            // Return a failure result with the exception if an error occurs
            Result.failure(e)
        }
    }

    // Private function to save a list of universities to the local database
    private suspend fun saveUniversitiesToLocal(universities: List<University>) {
        try {
            val entities = universities.map { UniversityMapper.toEntity(it) }
            // Insert all entities into the database
            universityDao.insertAll(entities)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    // Private function to fetch all universities from the local database
    private suspend fun getUniversitiesFromLocal(): List<University> {
        return try {
            val entities = universityDao.getAllUniversities()
            return entities.map { UniversityMapper.toUniversity(it) }
        } catch (e: Exception) {
            // Handle database exceptions here
            emptyList() // Return an empty list in case of error
        }
    }
}