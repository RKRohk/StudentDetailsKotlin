package com.example.newstudent.addStudentPage

import android.util.Log
import android.widget.EditText
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newstudent.db.Student
import com.example.newstudent.db.StudentDao
import kotlinx.coroutines.*

class AddStudentViewModel(val database:StudentDao): ViewModel(){
    var name:String? = null
    var rollno:String? = null
    var cgpa:String? = null
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    var _showToast = MutableLiveData<Boolean?>()
    var _student = MutableLiveData<Student?>()
    val student:LiveData<Student?>
        get() = _student
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
        _student.value = Student(name = name!!,rollNo = rollno!!.toInt(),cgpa = cgpa!!.toDouble(),id = 0)
        Log.i("DrumRoll",_student.value!!.name)
        uiScope.launch {
            insertToDatabase(_student.value)
        }
    }
    suspend fun insertToDatabase(student: Student?){
        withContext(Dispatchers.IO){
            var uid = database.insertStudent(student!!)
            if (uid == null)
                Log.i("DatabaseOperation","UID is NULL")
            else
                Log.i("DatabaseOperation","UID = $uid")
        }
        Log.i("DatabaseOperation", "Inserted to database ${student!!.name},${student!!.rollNo},${student!!.cgpa},${student!!.id}")
    }
}