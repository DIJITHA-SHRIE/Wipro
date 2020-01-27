package com.example.innovat.Database

import android.content.Context

import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.room.Database
import androidx.room.TypeConverters


@Database(
    entities = [RoomUser::class],
    version = 1
)
@TypeConverters(DataTypeConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): RoomDao


    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "user-list.db").allowMainThreadQueries()
            .build()
    }
}