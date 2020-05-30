package com.maxsch.symmetricguide.ui.login

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface LoginView : MvpView {

    @AddToEndSingle
    fun showContent()

    @Skip
    fun openMaterialsListScreen()

    @Skip
    fun showError()
}