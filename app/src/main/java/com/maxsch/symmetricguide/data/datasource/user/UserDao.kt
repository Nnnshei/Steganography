package com.maxsch.symmetricguide.data.datasource.user

import androidx.room.*
import com.maxsch.symmetricguide.entity.user.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): Single<User>

    @Insert
    fun addUser(user: User): Single<Unit>

    @Delete
    fun deleteUser(user: User): Single<Unit>

    @Update
    fun updateUser(user: User): Single<Unit>
}