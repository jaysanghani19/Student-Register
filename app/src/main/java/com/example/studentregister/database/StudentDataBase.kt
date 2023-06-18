package com.example.studentregister.database

import android.content.Context
import android.content.IntentFilter
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class] , version = 1, exportSchema = false)
abstract class StudentDataBase : RoomDatabase() {

    abstract fun studentDao() : StudentDao

    companion object{
        @Volatile
        private var INSTANCE : StudentDataBase ?= null
        fun getInstance(context : Context) : StudentDataBase{
            var instance = INSTANCE
            if (instance == null)
            {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                StudentDataBase::class.java,
                "student_data_table").build()
            }
            return instance
        }
    }
}