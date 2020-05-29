package com.maxsch.symmetricguide.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.maxsch.symmetricguide.R
import com.maxsch.symmetricguide.presentation.login.LoginPresenter
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get

class LoginFragment : MvpAppCompatFragment(R.layout.fragment_login), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter() = get<LoginPresenter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener {
            presenter.login(
                loginUsernameEditText.text.toString(),
                loginPasswordEditText.text.toString()
            )
        }
    }

    override fun openMaterialsListScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_nav_graph2)
    }

    override fun showContent() {
        progressBar.isVisible = false
        loginContentGroup.isVisible = true
    }

    override fun showError() {
        view?.let {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}