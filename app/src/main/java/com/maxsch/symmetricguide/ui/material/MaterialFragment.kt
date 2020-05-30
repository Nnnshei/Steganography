package com.maxsch.symmetricguide.ui.material

import android.os.Bundle
import android.view.View
import com.maxsch.symmetricguide.R
import com.maxsch.symmetricguide.entity.material.Material
import moxy.MvpAppCompatFragment

class MaterialFragment : MvpAppCompatFragment(R.layout.fragment_material), MaterialView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun loadData(material: Material) {

    }
}