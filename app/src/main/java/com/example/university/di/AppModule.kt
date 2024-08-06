package com.example.university.di

import android.content.Context
import androidx.room.Room
import com.example.university.data.local.UniversityDatabase
import com.example.university.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService.create()
    }
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): UniversityDatabase {
        return Room.databaseBuilder(
            appContext,
            UniversityDatabase::class.java,
            "university_db"
        ).build()
    }
    @Singleton
    @Provides
    fun provideUniversityDao(database: UniversityDatabase) = database.universityDao()
}
