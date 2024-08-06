package com.example.university.data.repository

import com.example.university.data.local.UniversityDao
import com.example.university.data.remote.ApiService
import com.example.university.data.remote.University
import com.example.university.util.NetworkUtil
import com.example.university.util.UniversityMapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class UniversityRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var networkUtil: NetworkUtil

    @Mock
    private lateinit var universityDao: UniversityDao

    private lateinit var repository: UniversityRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = UniversityRepository(apiService, networkUtil, universityDao)
    }

    @Test
    fun testGetUniversitiesByCountrySuccess() = runTest {
        // Arrange
        val country = "United Arab Emirates"
        val universities = listOf(
            University(
                alphaTwoCode = "AE",
                webPages = arrayListOf("www.test.com"),
                country = "United Arab Emirates",
                domains = arrayListOf("test.com"),
                name = "Mohamed bin Zayed University of Artificial Intelligence",
                stateProvince = "Abu Dhabi"
            )
        )
        `when`(networkUtil.isNetworkAvailable()).thenReturn(true)
        `when`(apiService.getUniversitiesByCountry(country)).thenReturn(universities)

        // Act
        val result = repository.getUniversitiesByCountry(country)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(universities, result.getOrNull())
        verify(apiService).getUniversitiesByCountry(country)
        verify(universityDao).insertAll(universities.map { UniversityMapper.toEntity(it) })
    }

    @Test
    fun testGetUniversitiesByCountryFailureNetworkError() = runTest {
        // Arrange
        val country = "United Arab Emirates"
        val exception = RuntimeException("Network error")
        `when`(networkUtil.isNetworkAvailable()).thenReturn(true)
        `when`(apiService.getUniversitiesByCountry(country)).thenThrow(exception)

        // Act
        val result = repository.getUniversitiesByCountry(country)

        // Assert
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verify(apiService).getUniversitiesByCountry(country)
        verify(universityDao, never()).insertAll(anyList())
    }

    @Test
    fun testGetUniversitiesByCountrySuccessFromLocal() = runTest {
        // Arrange
        val country = "United Arab Emirates"
        val localUniversities = listOf(
            University(
                alphaTwoCode = "AE",
                webPages = arrayListOf("www.test.com"),
                country = "United Arab Emirates",
                domains = arrayListOf("test.com"),
                name = "Mohamed bin Zayed University of Artificial Intelligence",
                stateProvince = "Abu Dhabi"
            )
        )
        `when`(networkUtil.isNetworkAvailable()).thenReturn(false)
        `when`(universityDao.getAllUniversities()).thenReturn(localUniversities.map { UniversityMapper.toEntity(it) })

        // Act
        val result = repository.getUniversitiesByCountry(country)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(localUniversities, result.getOrNull())
        verify(universityDao).getAllUniversities()
    }

    @Test
    fun testGetUniversitiesByCountryFailureNoLocalData() = runTest {
        // Arrange
        val country = "United Arab Emirates"
        `when`(networkUtil.isNetworkAvailable()).thenReturn(false)
        `when`(universityDao.getAllUniversities()).thenReturn(emptyList())

        // Act
        val result = repository.getUniversitiesByCountry(country)

        // Assert
        assertTrue(result.isFailure)
        assertEquals("No data found!", result.exceptionOrNull()?.message)
        verify(universityDao).getAllUniversities()
    }
}
