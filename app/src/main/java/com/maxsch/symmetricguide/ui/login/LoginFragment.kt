package com.maxsch.symmetricguide.ui.login

import android.os.Bundle
import android.view.View
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

        loginRegisterButton.setOnClickListener {
            openRegisterScreen()
        }
    }

    override fun openPincodeScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_сheckPincodeFragment)
    }

    override fun showError() {
        view?.let {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun openRegisterScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
    }
}