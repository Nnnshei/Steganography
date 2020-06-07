package com.maxsch.symmetricguide.ui.pincode

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.maxsch.symmetricguide.R
import com.maxsch.symmetricguide.entity.session.repository.SessionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pincode.*
import moxy.MvpAppCompatFragment
import org.koin.android.ext.android.inject

class CheckPincodeFragment : MvpAppCompatFragment(R.layout.fragment_pincode) {

    private var disposable: Disposable? = null
    private val sessionRepositoryImpl: SessionRepository by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCheckPin.setOnClickListener { checkPin() }
    }

    fun checkPin() {
        disposable = sessionRepositoryImpl.getPin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (textPincode.text.toString() == it) {
                    openMenuScreen()
                } else {
                    textWrongPin.setText("Неверный PIN")
                }
            }, {

            })
    }

    fun openMenuScreen() {
        findNavController().navigate(R.id.action_сheckPincodeFragment_to_menuFragment)
    }
}