package com.example.newstudent.homePage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newstudent.db.Student
import com.example.newstudent.db.StudentDao
import kotlinx.coroutines.*

class HomePageViewModel(val database:StudentDao) : ViewModel(){

    var _navigate = MutableLiveData<Boolean?>()

    val viewModelJob = Job()

    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var _newestStudent = MutableLiveData<Student?>()

    val newestStudent:LiveData<Student?>
        get() = _newestStudent

    fun setNewStudent(){
        uiScope.launch {
            _newestStudent.value = getNewStudent()
            if (_newestStudent.value == null)
                Log.i("DatabaseOperation","Database null for some reason")
        }
        Log.i("DatabaseOperation","Got new student")
    }

    suspend fun getNewStudent():Student?{
        return withContext(Dispatchers.IO){
            var student:Student? = database.getLastStudent()
            student
        }.also { Log.i("DatabaseOperation","Returning Student") }
    }

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
        setNewStudent()
    }
}