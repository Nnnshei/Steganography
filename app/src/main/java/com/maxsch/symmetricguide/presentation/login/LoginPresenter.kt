package com.maxsch.symmetricguide.presentation.login

import android.util.Log
import com.maxsch.symmetricguide.entity.session.repository.SessionRepository
import com.maxsch.symmetricguide.entity.user.repository.UserRepository
import com.maxsch.symmetricguide.presentation.BasePresenter
import com.maxsch.symmetricguide.ui.login.LoginView
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState

@InjectViewState
class LoginPresenter(
    private val sessionRepositoryImpl: SessionRepository,
    private val userRepository: UserRepository
) : BasePresenter<LoginView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        sessionRepositoryImpl.getActiveSession()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                proceedToMaterialsList()
            }, {
                Log.e("${this::class}", "$it")
                viewState.showContent()
            })
            .untilDestroy()
    }

    fun login(username: String, password: String) {
        userRepository.getUserByUsername(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapCompletable {
                if (password == it.password)
                    sessionRepositoryImpl.saveSession(username, password)
                else
                    Completable.error(Throwable("User not found"))
            }
            .subscribe({
                proceedToMaterialsList()
            }, {
                Log.e("${this::class}", "$it")
                viewState.showError()
            })
            .untilDestroy()
    }

    private fun proceedToMaterialsList() {
        viewState.openMaterialsListScreen()
    }
}