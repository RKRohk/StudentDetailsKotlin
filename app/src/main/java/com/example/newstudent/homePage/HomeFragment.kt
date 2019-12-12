package com.example.newstudent.homePage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.newstudent.R
import com.example.newstudent.databinding.FragmentHomeBinding
import com.example.newstudent.db.StudentDataBase
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = StudentDataBase.getInstance(application).studentDao
        val viewModelFactory = HomePageViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(viewModelStore,viewModelFactory).get(HomePageViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.navigate.observe(this, Observer {
            if (viewModel.navigate.value == true){
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddStudentFragment()).also { viewModel.doneNavigating() }
            }
        })
        return binding.root
    }

}
