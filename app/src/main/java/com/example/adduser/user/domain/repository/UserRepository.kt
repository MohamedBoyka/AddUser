package com.example.adduser.user.domain.repository

import com.example.adduser.user.domain.entities.UserEntity

interface UserRepository {
    suspend fun getUsers(): List<UserEntity>
}