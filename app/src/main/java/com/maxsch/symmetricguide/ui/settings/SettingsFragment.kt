package com.maxsch.symmetricguide.ui.settings

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.maxsch.symmetricguide.R
import com.maxsch.symmetricguide.presentation.settings.SettingsPresenter
import kotlinx.android.synthetic.main.fragment_settings.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get

class SettingsFragment : MvpAppCompatFragment(R.layout.fragment_settings), SettingsView {

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter() = get<SettingsPresenter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsLogout.setOnClickListener {
            logout()
        }
    }

    override fun navigateToLogin() {
        findNavController().navigate(R.id.action_SettingsFragment_to_loginFragment)
    }

    private fun logout() {
        presenter.logout()
    }
}
