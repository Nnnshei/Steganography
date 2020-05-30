package com.maxsch.symmetricguide.data.repository.session

import com.maxsch.symmetricguide.data.datasource.session.SessionDataSource
import com.maxsch.symmetricguide.entity.session.Session
import com.maxsch.symmetricguide.entity.session.repository.SessionRepository
import io.reactivex.Completable
import io.reactivex.Single

class SessionRepositoryImpl(
    private val dataSource: SessionDataSource
) : SessionRepository {

    override fun saveSession(username: String, password: String): Completable =
        Completable.fromAction {
            dataSource.session =
                Session(
                    username,
                    password
                )
        }

    override fun getActiveSession(): Single<Session> =
        Single.fromCallable {
            dataSource.session
        }

    override fun clearSession(): Single<Unit> =
        Single.fromCallable {
            dataSource.session = null
        }
}