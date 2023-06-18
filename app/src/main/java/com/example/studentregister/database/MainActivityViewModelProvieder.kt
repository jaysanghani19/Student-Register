package com.example.studentregister.database

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentregister.MainActivityViewModel

class MainActivityViewModelProvieder(private val dao: StudentDao) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(dao) as T
        }
        throw IllegalArgumentException("No class found")
    }


}