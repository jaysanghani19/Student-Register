package com.example.studentregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentregister.database.Student
import com.example.studentregister.database.StudentDao
import kotlinx.coroutines.launch

class MainActivityViewModel(val dao: StudentDao) : ViewModel() {
    val students = dao.getAllData()

    fun insertData(student: Student) = viewModelScope.launch {
        dao.insertData(student)
    }

    fun deleteData(student: Student)= viewModelScope.launch {
        dao.deleteData(student)
    }

    fun updateData(student: Student) = viewModelScope.launch {
        dao.updateData(student)
    }
}