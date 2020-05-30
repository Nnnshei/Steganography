package com.maxsch.symmetricguide.ui.material.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.maxsch.symmetricguide.R
import kotlinx.android.synthetic.main.fragment_materials_list.*
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment

class MaterialsListFragment : MvpAppCompatFragment(R.layout.fragment_materials_list),
    MaterialsListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MvpAppCompatActivity).setSupportActionBar(toolbar)
        materialsAddButton.setOnClickListener {
//            findNavController().navigate(R.id.action_MaterialsFragment_to_materialFragment)
        }
    }

    override fun showMaterials() {

    }

    private fun openSettingsScreen() {
        findNavController().navigate(R.id.action_MaterialsFragment_to_SettingsFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> openSettingsScreen()
        }
        return super.onOptionsItemSelected(item)
    }
}
