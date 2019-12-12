package com.example.newstudent.addStudentPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newstudent.db.StudentDao

class AddStudentPageViewModelFactory(private val database:StudentDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddStudentViewModel(database) as T
    }

}