package com.example.university.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UniversityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<UniversityEntity>)

    /*@Query("SELECT * FROM universities WHERE name = :name")
    suspend fun getUniversityByName(name: String): UniversityEntity?*/

    /*@Query("SELECT * FROM universities")
    suspend fun getAllUniversities(): List<UniversityEntity>*/
}
