package com.maxsch.symmetricguide.data.datasource.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxsch.symmetricguide.entity.user.User

@Database(entities = [User::class], version = 2)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}