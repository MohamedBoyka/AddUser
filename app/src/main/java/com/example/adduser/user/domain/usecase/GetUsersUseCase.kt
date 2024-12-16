package com.example.adduser.user.domain.usecase

import com.example.adduser.user.domain.entities.UserEntity
import com.example.adduser.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): List<UserEntity>? {
        return repository.getUsers()
    }
}