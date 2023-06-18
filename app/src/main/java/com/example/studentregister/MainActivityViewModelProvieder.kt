package com.example.studentregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentregister.database.StudentDao

class MainActivityViewModelProvieder(private val dao: StudentDao) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(dao) as T
        }
        throw IllegalArgumentException("Illegal Class")
    }


}