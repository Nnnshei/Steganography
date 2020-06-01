package com.maxsch.symmetricguide.ui.menu

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.maxsch.symmetricguide.R
import kotlinx.android.synthetic.main.fragment_menu.*
import moxy.MvpAppCompatFragment

class MenuFragment : MvpAppCompatFragment(R.layout.fragment_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnEncodeScreen.setOnClickListener { openEncodingScreen() }
        btnDecodeScreen.setOnClickListener { openDecodingScreen() }
        btnSettingsScreen.setOnClickListener { openSettingsScreen() }
    }

    fun openEncodingScreen() {
        findNavController().navigate(R.id.action_menuFragment_to_encodingFragment2)
    }

    fun openDecodingScreen() {
        findNavController().navigate(R.id.action_menuFragment_to_decodingFragment)
    }

    fun openSettingsScreen() {
        findNavController().navigate(R.id.action_menuFragment_to_SettingsFragment)
    }
}