package com.example.studentregister.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Declare Tablename
@Entity(tableName = "student_data_table")
data class Student(
//    To save the data Automatically
    @PrimaryKey(autoGenerate = true)
//    ColumnInfo is for declaring the column of the database table
    @ColumnInfo(name = "student_id")
    var id : Int,
    @ColumnInfo(name = "student_name")
    var name : String,
    @ColumnInfo(name = "student_email")
    var email : String
)