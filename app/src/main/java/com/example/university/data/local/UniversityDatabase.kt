package com.example.university.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.university.data.remote.University
import com.example.university.util.Converters

@Database(entities = [University::class], version = 1)
@TypeConverters(Converters::class)
abstract class UniversityDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao
}
