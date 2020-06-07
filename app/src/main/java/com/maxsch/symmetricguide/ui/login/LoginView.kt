package com.maxsch.symmetricguide.ui.login

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface LoginView : MvpView {


    @Skip
    fun openPincodeScreen()

    @Skip
    fun showError()
}