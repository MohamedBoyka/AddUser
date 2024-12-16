package com.example.adduser.addUser.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.adduser.databinding.FragmentAddUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment() {


    private lateinit var binding : FragmentAddUserBinding
    private val viewModel : AddUserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.error.observe(viewLifecycleOwner){
            if (it != null && !it.isNullOrEmpty()){
                Toast.makeText(context , "$it" , Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.success.observe(viewLifecycleOwner){
            if(it !=null && it){
                Toast.makeText(context , "Success :)" , Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}