package com.maxsch.symmetricguide.entity.user.repository

import com.maxsch.symmetricguide.entity.user.User
import io.reactivex.Single

interface UserRepository {

    fun getUserByUsername(username: String): Single<User>

    fun registerUser(user: User): Single<Unit>

    fun updateUser(user: User): Single<Unit>

    fun deleteUser(user: User): Single<Unit>
}