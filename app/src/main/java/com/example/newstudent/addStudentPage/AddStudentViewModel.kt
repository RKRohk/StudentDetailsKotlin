package com.example.newstudent.addStudentPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddStudentViewModel: ViewModel(){
    var name:String? = null
    var rollno:String? = null
    var cgpa:String? = null
    var _showToast = MutableLiveData<Boolean?>()
    val showToast:LiveData<Boolean?>
        get() = _showToast
    fun doneToast(){
        _showToast.value = null
    }
    init {
        _showToast.value = false
    }
    fun onButtonPressed(){
        _showToast.value = true
    }

}