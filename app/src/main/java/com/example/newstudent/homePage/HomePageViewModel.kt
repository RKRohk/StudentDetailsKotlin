package com.example.newstudent.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomePageViewModel : ViewModel(){

    var _navigate = MutableLiveData<Boolean?>()

    val navigate:LiveData<Boolean?>
        get() = _navigate

    fun initialize(){
        _navigate.value = false
    }

    fun onButtonClicked(){
        _navigate.value = true
    }
    fun doneNavigating(){
        _navigate.value = null
    }
    init {
        initialize()
    }
}