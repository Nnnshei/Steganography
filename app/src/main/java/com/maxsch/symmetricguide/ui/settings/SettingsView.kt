package com.maxsch.symmetricguide.ui.settings

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

@Skip
interface SettingsView : MvpView {

    fun navigateToLogin()
}