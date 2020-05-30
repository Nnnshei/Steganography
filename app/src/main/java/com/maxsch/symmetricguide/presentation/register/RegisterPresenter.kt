package com.maxsch.symmetricguide.presentation.register

import com.maxsch.symmetricguide.entity.session.repository.SessionRepository
import com.maxsch.symmetricguide.entity.user.User
import com.maxsch.symmetricguide.entity.user.repository.UserRepository
import com.maxsch.symmetricguide.presentation.BasePresenter
import com.maxsch.symmetricguide.ui.register.RegisterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterPresenter(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) : BasePresenter<RegisterView>() {

    fun register(username: String, password: String) {
        userRepository.registerUser(User(null, username, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapCompletable {
                sessionRepository.saveSession(username, password)
            }
            .subscribe({
                viewState.login()
            }, {

            })
            .untilDestroy()
    }
}