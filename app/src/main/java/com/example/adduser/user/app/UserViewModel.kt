package com.example.adduser.user.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adduser.user.domain.entities.UserEntity
import com.example.adduser.user.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<List<UserEntity>>()
    val users: LiveData<List<UserEntity>> get() = _users

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

/*    init {
        loadUsers()

    }*/

     fun loadUsers() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _users.value = getUsersUseCase()!!
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
                _error.value = "failed to get users: ${e.message}"
            }
        }
    }

}
