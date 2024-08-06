package com.example.university.data.local

import com.example.university.data.remote.University

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversity(university: University)

    @Query("SELECT * FROM universities")
    suspend fun getAllUniversities(): List<University>
}
