package com.example.adduser.addUser.domain.usecase


import com.example.adduser.addUser.data.repository.AddUserRepositoryImpl
import com.example.adduser.user.domain.entities.UserEntity
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val repository: AddUserRepositoryImpl) {
    suspend operator fun invoke(user: UserEntity) {
        repository.addUser(user)
    }
}