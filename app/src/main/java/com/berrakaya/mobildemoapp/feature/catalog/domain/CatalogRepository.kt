package com.berrakaya.mobildemoapp.feature.catalog.domain

import com.berrakaya.mobildemoapp.feature.catalog.domain.model.Product
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.ProductGroup

interface CatalogRepository {
    suspend fun getProductGroups(): List<ProductGroup>
    suspend fun getProductsByGroup(groupId: String): List<Product>
    suspend fun getProduct(productId: String): Product?
}