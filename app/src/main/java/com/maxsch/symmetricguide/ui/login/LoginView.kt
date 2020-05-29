package com.maxsch.symmetricguide.ui.login

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface LoginView : MvpView {

    fun showContent()

    fun openMaterialsListScreen()

    fun showError()
}