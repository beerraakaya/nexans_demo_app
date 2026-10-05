package com.berrakaya.mobildemoapp.feature.catalog.domain.model

import com.berrakaya.mobildemoapp.core.model.LocalizedText

data class Product(
    val id: String,
    val groupId: String,
    val code: String,
    val name: LocalizedText,
    val description: LocalizedText,
    val specs: List<ProductSpec>,
)

data class ProductSpec(
    val label: LocalizedText,
    val value: String,
)