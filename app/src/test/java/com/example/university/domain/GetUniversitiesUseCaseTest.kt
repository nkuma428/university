package com.example.university.domain

import com.example.university.data.remote.University
import com.example.university.data.repository.UniversityRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class GetUniversitiesUseCaseTest {

    private lateinit var repository: UniversityRepository
    private lateinit var getUniversitiesUseCase: GetUniversitiesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = mock(UniversityRepository::class.java)
        getUniversitiesUseCase = GetUniversitiesUseCase(repository)
    }

    @Test
    fun testGetUniversitiesByCountrySuccess() = runTest {
        // Arrange
        val country = "United Arab Emirates"
        val expectedUniversities = listOf(
            University(
                alphaTwoCode = "AE",
                webPages = arrayListOf("www.test.com"),
                country = "United Arab Emirates",
                domains = arrayListOf("test.com"),
                name = "Mohamed bin Zayed University of Artificial Intelligence",
                stateProvince = "Abu Dhabi"
            )
        )
        `when`(repository.getUniversitiesByCountry(country)).thenReturn(Result.success(expectedUniversities))

        // Act
        val result = getUniversitiesUseCase(country)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(expectedUniversities, result.getOrNull())
        verify(repository).getUniversitiesByCountry(country)
    }

    @Test
    fun testGetUniversitiesByCountryFailure() = runTest {
        // Arrange
        val country = "United Arab Emirates"
        val expectedException = RuntimeException("Network error")
        `when`(repository.getUniversitiesByCountry(country)).thenReturn(Result.failure(expectedException))

        // Act
        val result = getUniversitiesUseCase(country)

        // Assert
        assertTrue(result.isFailure)
        assertEquals(expectedException, result.exceptionOrNull())
        verify(repository).getUniversitiesByCountry(country)
    }
}