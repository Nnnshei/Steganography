package com.maxsch.symmetricguide.data.datasource.user

import androidx.room.Dao
import androidx.room.Query
import com.maxsch.symmetricguide.entity.user.User
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): Single<User>
}