package com.berrakaya.mobildemoapp.feature.catalog.domain.model

import com.berrakaya.mobildemoapp.core.model.LocalizedText

data class ProductGroup(
    val id: String,
    val name: LocalizedText,
    val description: LocalizedText,
    val productCount: Int,
)