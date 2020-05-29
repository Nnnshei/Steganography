package com.maxsch.symmetricguide.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<T : MvpView>() : MvpPresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

    fun Disposable.untilDestroy() = compositeDisposable.add(this)

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}