package com.maxsch.symmetricguide.entity.user.repository

import com.maxsch.symmetricguide.entity.user.User
import io.reactivex.Single

interface UserRepository {

    fun getUserByUsername(username: String): Single<User>
}