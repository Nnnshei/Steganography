package com.maxsch.symmetricguide.ui.material.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxsch.symmetricguide.R
import com.maxsch.symmetricguide.entity.material.Material
import kotlinx.android.extensions.LayoutContainer

class MaterialsAdapter(
    private val materialClickListener: () -> Int
) : RecyclerView.Adapter<MaterialsAdapter.ViewHolder>() {

    var items = mutableListOf<Material>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            View.inflate(parent.context, R.layout.item_material, parent),
            materialClickListener
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val materialClickListener: () -> Int
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(material: Material) {

        }
    }
}