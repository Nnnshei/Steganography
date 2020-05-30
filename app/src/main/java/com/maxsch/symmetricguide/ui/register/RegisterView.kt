package com.maxsch.symmetricguide.ui.register

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@Skip
interface RegisterView : MvpView {

    fun login()
}