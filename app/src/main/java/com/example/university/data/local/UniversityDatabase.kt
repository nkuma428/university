package com.example.university.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UniversityEntity::class], version = 1, exportSchema = false)
abstract class UniversityDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao

    companion object {
        private fun buildDatabase(context: Context): UniversityDatabase {
            return Room.databaseBuilder(context, UniversityDatabase::class.java, "university_database").build()
        }

        @Volatile
        private var INSTANCE: UniversityDatabase? = null

        fun getDataBaseInstance(context: Context) : UniversityDatabase {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }
    }

//    companion object {
//        @Volatile
//        private var INSTANCE: UniversityDatabase? = null
//
//        /*fun getDatabase(context: Context): UniversityDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    UniversityDatabase::class.java,
//                    "university_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }*/
//    }
}