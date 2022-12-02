package com.example.todousingroomdb

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context



@Database(entities = [User::class], version = 1)
  abstract class AppDatabase : RoomDatabase() {

    abstract val appDao: AppDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            var instance = INSTANCE
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, AppDatabase::class.java, "chetuTm").build()
                    INSTANCE = instance
                }
            }
            return instance!!

        }
    }
}