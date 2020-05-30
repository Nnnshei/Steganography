package com.maxsch.symmetricguide.ui.material

import com.maxsch.symmetricguide.entity.material.Material
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@Skip
interface MaterialView {

    fun loadData(material: Material)
}