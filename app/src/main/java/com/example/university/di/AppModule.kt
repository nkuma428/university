package com.example.university.di

import android.content.Context
import androidx.room.Room
import com.example.university.data.local.UniversityDao
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

//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): UniversityDatabase {
//        return Room.databaseBuilder(
//            context,
//            UniversityDatabase::class.java,
//            "university_database"
//        ).build()
//    }

    /*@Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): UniversityDatabase {
        return Room
            .databaseBuilder(appContext, UniversityDatabase::class.java, "university_database")
            .build()
    }*/

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): UniversityDatabase {
        return Room.databaseBuilder(appContext, UniversityDatabase::class.java, "university_database").build()
    }

    /*@Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        UniversityDatabase::class.java,
        "university_database"
    ).build()*/


    @Provides
    @Singleton
    fun provideUniversityDao(database: UniversityDatabase): UniversityDao {
        /*val db = Room.databaseBuilder(
            context.applicationContext,
            UniversityDatabase::class.java,
            "university_database"
        ).build()*/
        return database.universityDao()
    }

    /*@Provides
    @Singleton
    fun provideUniversityRepository(
        api: ApiService,
        dao: UniversityDao,
        @ApplicationContext context: Context
    ) = UniversityRepository(api, dao, context)*/
}
