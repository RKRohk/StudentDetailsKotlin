package com.example.newstudent.addStudentPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.newstudent.R
import com.example.newstudent.databinding.FragmentAddStudentBinding
import com.example.newstudent.db.StudentDao
import com.example.newstudent.db.StudentDataBase

/**
 * A simple [Fragment] subclass.
 */
class AddStudentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentAddStudentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_student,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = StudentDataBase.getInstance(application).studentDao
        val viewModelFactory = AddStudentPageViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(viewModelStore,viewModelFactory).get(AddStudentViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.showToast.observe(this, Observer {
            if (it == true)
                Toast.makeText(context,"CGPA ${viewModel.cgpa}",Toast.LENGTH_SHORT).show().also { viewModel.doneToast() }
        })
        return binding.root
    }

}
