package com.example.newstudent.addStudentPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddStudentPageViewModelFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddStudentViewModel() as T
    }

}