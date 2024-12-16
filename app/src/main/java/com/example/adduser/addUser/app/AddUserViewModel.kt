package com.example.adduser.addUser.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adduser.addUser.domain.usecase.AddUserUseCase
import com.example.adduser.user.domain.entities.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase) : ViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    val userNameEditText = MutableLiveData<String>()
    val ageEditText = MutableLiveData<String>()
    val jobTitleEditText = MutableLiveData<String>()
    val genderEditText = MutableLiveData<String>()

    val isButtonEnabled: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(userNameEditText) { value = checkFields() }
        addSource(ageEditText) { value = checkFields() }
        addSource(jobTitleEditText) { value = checkFields() }
        addSource(genderEditText) { value = checkFields() }
    }

    private fun checkFields(): Boolean {
        return !userNameEditText.value.isNullOrEmpty() &&
                !ageEditText.value.isNullOrEmpty() &&
                !jobTitleEditText.value.isNullOrEmpty() &&
                !genderEditText.value.isNullOrEmpty()
    }

    fun onAddUserChanged(s: CharSequence, editTextId: Int) {
        when (editTextId) {
            1 -> userNameEditText.value = s.toString()
            2 -> ageEditText.value = s.toString()
            3 -> jobTitleEditText.value = s.toString()
            4 -> genderEditText.value = s.toString()
        }
    }

    fun addUser() {
        _success.value = false
            val user = UserEntity(
                name = userNameEditText.value.toString(),
                age = ageEditText.value.toString(),
                jobTitle = jobTitleEditText.value.toString(),
                gender = genderEditText.value.toString()
            )
            viewModelScope.launch {
                try {
                    addUserUseCase(user)
                    _success.value = true
                    clearEditText()
                } catch (e: Exception) {
                    _success.value = false
                    _error.value = "${e.message}"
                }
            }

    }

    private fun clearEditText(){
       userNameEditText.value = ""
        ageEditText.value = ""
        jobTitleEditText.value = ""
       genderEditText.value = ""
    }
}