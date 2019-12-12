package com.example.newstudent.homePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newstudent.db.StudentDao

class HomePageViewModelFactory(private val database: StudentDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomePageViewModel(database) as T
    }

}