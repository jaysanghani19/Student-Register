package com.example.studentregister.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Insert
    suspend fun insertData(student: Student)

    @Update
    suspend fun updateData(student: Student)

    @Delete
    suspend fun deleteData(student: Student)

//    Room uses his own dispatchers for the executing the task
//    that's why we don't need suspended function
    @Query("SELECT * FROM student_data_table")
    fun getAllData() : LiveData<List<Student>>
}