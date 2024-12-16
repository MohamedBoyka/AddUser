package com.example.adduser.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.adduser.user.domain.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>
}