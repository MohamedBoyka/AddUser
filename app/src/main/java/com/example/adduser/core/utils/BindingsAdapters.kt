package com.example.adduser.core.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adduser.R
import com.example.adduser.addUser.app.AddUserViewModel
import com.example.adduser.user.app.UserAdapter
import com.example.adduser.user.domain.entities.UserEntity

@BindingAdapter("app:onTextChanged")
fun setOnTextChangedListener(editText: EditText, viewModel: AddUserViewModel) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val editTextId = when (editText.id) {
                R.id.ed_user_name-> 1
                R.id.ed_age -> 2
                R.id.ed_job_title -> 3
                R.id.txt_gender -> 4
                else -> 0
            }
            viewModel.onAddUserChanged(s ?: "", editTextId)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

@BindingAdapter("userItem")
fun bindUserItemsRecyclerView(recyclerView: RecyclerView, data: List<UserEntity>?) {
    recyclerView.adapter?.let {
        val adapter = it as UserAdapter
        adapter.submitList(data)
    }
}

