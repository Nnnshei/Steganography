package com.maxsch.symmetricguide.entity.material.repository

import com.maxsch.symmetricguide.entity.material.Material
import io.reactivex.Single

interface MaterialRepository {

    fun getMaterials(): Single<List<Material>>

    fun getMaterialById(id: Int): Single<Material>

    fun updateMaterial(updatedMaterial: Material): Single<Unit>

    fun deleteMaterial(material: Material): Single<Unit>

    fun addMaterial(material: Material): Single<Unit>
}