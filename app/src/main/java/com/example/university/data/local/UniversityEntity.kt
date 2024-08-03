package com.example.university.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "universities")
data class UniversityEntity(
    @PrimaryKey val name: String,
    val country: String,
    val alpha_two_code: String,
    val web_pages: List<String>
)
