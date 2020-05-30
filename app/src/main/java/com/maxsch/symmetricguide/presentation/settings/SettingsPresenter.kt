package com.maxsch.symmetricguide.presentation.settings

import com.maxsch.symmetricguide.entity.session.repository.SessionRepository
import com.maxsch.symmetricguide.presentation.BasePresenter
import com.maxsch.symmetricguide.ui.settings.SettingsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState

@InjectViewState
class SettingsPresenter(
    private val sessionRepository: SessionRepository
) : BasePresenter<SettingsView>() {

    fun logout() {
        sessionRepository.clearSession()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.navigateToLogin()
            }, {

            })
            .untilDestroy()
    }
}