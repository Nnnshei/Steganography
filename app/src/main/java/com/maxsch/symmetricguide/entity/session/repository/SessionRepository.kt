package com.maxsch.symmetricguide.entity.session.repository

import com.maxsch.symmetricguide.entity.session.Session
import io.reactivex.Completable
import io.reactivex.Single

interface SessionRepository {

    fun saveSession(username: String, password: String): Completable

    fun getActiveSession(): Single<Session>

    fun clearSession(): Single<Unit>

    fun changePin(pin: String): Completable

    fun getPin(): Single<String>
}