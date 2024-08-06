package com.example.university.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UniversityDao {

    // Inserts a list of university entities into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<UniversityEntity>)

    // Queries the database to retrieve all university entities
    @Query("SELECT * FROM universities")
    suspend fun getAllUniversities(): List<UniversityEntity>
}
