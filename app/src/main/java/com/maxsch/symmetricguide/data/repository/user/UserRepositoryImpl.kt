package com.maxsch.symmetricguide.data.repository.user

import com.maxsch.symmetricguide.data.datasource.user.UserDao
import com.maxsch.symmetricguide.entity.user.User
import com.maxsch.symmetricguide.entity.user.repository.UserRepository
import io.reactivex.Single

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {

    override fun getUserByUsername(username: String): Single<User> = dao.getUserByUsername(username)

    override fun registerUser(user: User): Single<Unit> = dao.addUser(user)

    override fun updateUser(user: User): Single<Unit> = dao.updateUser(user)

    override fun deleteUser(user: User): Single<Unit> = dao.deleteUser(user)
}