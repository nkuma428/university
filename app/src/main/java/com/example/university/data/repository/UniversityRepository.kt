package com.example.university.data.repository


import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.university.data.local.UniversityDao
import com.example.university.data.remote.ApiService
import com.example.university.data.remote.University
import com.example.university.util.NetworkUtil
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val apiService: ApiService,
    private val networkUtil: NetworkUtil
    /*,
    private val universityDao: UniversityDao,
    private val context: Context*/

) {
    suspend fun getUniversitiesByCountry(country: String): List<University> {
        return if (networkUtil.isNetworkAvailable()) {
            Log.e("===","Network Available")
            val universities = apiService.getUniversitiesByCountry(country)
            //saveUniversitiesToLocal(universities)
            universities
        } else {
            Log.e("===","Network Not Available")
            getUniversitiesFromLocal()
        }
    }

    private suspend fun saveUniversitiesToLocal(universities: List<University>) {
        /*val entities = universities.map {
            UniversityEntity(
                name = it.name,
                country = it.country,
                alpha_two_code = it.alpha_two_code
            )
        }*/
        //universityDao.insertAll(entities)
    }

    private suspend fun getUniversitiesFromLocal(): List<University> {
        /*return universityDao.getAllUniversities().map {
            University(
                name = it.name,
                country = it.country,
                alpha_two_code = it.alpha_two_code,
                web_pages = null
            )
        }*/
        return listOf<University>()
    }
}