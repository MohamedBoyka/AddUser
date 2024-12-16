package com.example.adduser.core.di

import android.content.Context
import androidx.room.Room
import com.example.adduser.addUser.data.repository.AddUserRepositoryImpl
import com.example.adduser.addUser.domain.repository.AddUserRepository
import com.example.adduser.core.database.UserDatabase
import com.example.adduser.core.database.UserDao
import com.example.adduser.user.data.repository.UserRepositoryImpl
import com.example.adduser.user.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    fun provideAddUserRepository(userDao: UserDao): AddUserRepository {
        return AddUserRepositoryImpl(userDao)
    }

}
