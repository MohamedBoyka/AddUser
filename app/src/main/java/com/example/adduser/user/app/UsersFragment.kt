package com.example.adduser.user.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adduser.R
import com.example.adduser.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding : FragmentUsersBinding
    private val viewModel :UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        userAdapter = UserAdapter().also {
            binding.rvGetUser.adapter = it
        //    userAdapter.notifyDataSetChanged()
        }

      /*  viewModel.users.observe(viewLifecycleOwner){
            if (it != null) {
                userAdapter.notifyDataSetChanged()
             //   userAdapter.submitList(it)
            }
        }*/
        binding.btnAddUser.setOnClickListener{
            findNavController().navigate(UsersFragmentDirections.actionUsersFragmentToAddUserFragment())
        }

        binding.btnAddUserIsEmpty.setOnClickListener{
            findNavController().navigate(UsersFragmentDirections.actionUsersFragmentToAddUserFragment())
        }

        return binding.root

    }

    override fun onStart() {
        super.onStart()
        viewModel.loadUsers()
    }

}