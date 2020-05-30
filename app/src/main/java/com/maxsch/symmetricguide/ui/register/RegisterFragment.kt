package com.maxsch.symmetricguide.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.maxsch.symmetricguide.R
import com.maxsch.symmetricguide.presentation.register.RegisterPresenter
import kotlinx.android.synthetic.main.fragment_register.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get

class RegisterFragment : MvpAppCompatFragment(R.layout.fragment_register), RegisterView {

    @InjectPresenter
    lateinit var presenter: RegisterPresenter

    @ProvidePresenter
    fun providePresenter() = get<RegisterPresenter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerButton.setOnClickListener {
            presenter.register(registerUsername.text.toString(), registerPassword.text.toString())
        }
    }

    override fun login() {
        findNavController().navigate(R.id.action_registerFragment_to_MaterialsFragment)
    }
}