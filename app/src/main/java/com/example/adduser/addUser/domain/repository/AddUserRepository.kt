package com.example.adduser.addUser.domain.repository

import com.example.adduser.user.domain.entities.UserEntity

interface AddUserRepository {
    suspend fun addUser(user: UserEntity)

}