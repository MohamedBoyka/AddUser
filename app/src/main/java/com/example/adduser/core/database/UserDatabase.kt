package com.example.adduser.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adduser.user.domain.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}