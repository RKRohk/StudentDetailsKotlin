package com.example.newstudent.homePage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newstudent.db.Student
import com.example.newstudent.db.StudentDao
import kotlinx.coroutines.*

class HomePageViewModel(private val database:StudentDao) : ViewModel(){

    var _navigate = MutableLiveData<Boolean?>()

    val viewModelJob = Job()

    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var lastStudent:LiveData<Student>? = null
    fun setNewStudent(){
        uiScope.launch {
            lastStudent = getStudent()
            if (lastStudent== null) {
                Log.i("DatabaseOperation","Database null for some reason")
            }
        }
    }
    suspend fun getStudent():LiveData<Student>?{
        return withContext(Dispatchers.IO){
            var student = database.getLastStudent()
            student
        }
    }
    val navigate:LiveData<Boolean?>
        get() = _navigate

    private fun initialize(){
        _navigate.value = false
    }

    fun onButtonClicked(){
        _navigate.value = true
    }
    fun doneNavigating(){
        _navigate.value = null
    }
    init {
        setNewStudent()
        initialize()
    }
}