package com.example.newstudent.studentList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData

import com.example.newstudent.R
import com.example.newstudent.databinding.FragmentStudentListBinding
import com.example.newstudent.db.Student
import com.example.newstudent.db.StudentDao
import com.example.newstudent.db.StudentDataBase
import kotlinx.coroutines.*

class student_list : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentStudentListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_student_list,container,false)


        val application = requireNotNull(this.activity).application
        val dataSource = StudentDataBase.getInstance(application).studentDao
        binding.lifecycleOwner = this
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)
        var students:List<Student>? = null
        uiScope.launch {
            students = getAllStudentsList(dataSource)
            Log.i("DatabaseOperation","Last student is ${getStudent(dataSource)?.value?.name}")
            students?: Log.i("DatabaseOperation","Students are null")
            students?.let {
                val adapter = studentListAdapter(students!!)
                Log.i("DatabaseOperation","Got ${students!!.size} Students")
                binding.studentVIew.adapter = adapter
                binding.studentVIew.adapter?.notifyDataSetChanged()
            }
        }

        return binding.root
    }

    suspend fun getAllStudentsList(dataBase: StudentDao): List<Student> {
        return withContext(Dispatchers.IO){
            var list = dataBase.getStudentsList()
            list
        }
    }
    suspend fun getStudent(dataBase: StudentDao):LiveData<Student>?{
        return withContext(Dispatchers.IO){
            var student = dataBase.getLastStudent()
            student
        }
    }

}
