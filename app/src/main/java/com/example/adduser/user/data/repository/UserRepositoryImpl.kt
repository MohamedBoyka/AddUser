package com.example.adduser.user.data.repository

import com.example.adduser.core.database.UserDao
import com.example.adduser.user.domain.entities.UserEntity
import com.example.adduser.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun getUsers(): List<UserEntity> {
        return userDao.getAllUsers().map {
            UserEntity(
                name = it.name,
                age = it.age,
                jobTitle = it.jobTitle,
                gender = it.gender
            )
        }
    }
}