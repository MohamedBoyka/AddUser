package com.example.adduser.addUser.data.repository

import com.example.adduser.addUser.domain.repository.AddUserRepository
import com.example.adduser.core.database.UserDao
import com.example.adduser.user.domain.entities.UserEntity
import javax.inject.Inject

class AddUserRepositoryImpl  @Inject constructor(private val userDao: UserDao) : AddUserRepository {

    override suspend fun addUser(user: UserEntity) {
        userDao.insertUser(user)
    }
}