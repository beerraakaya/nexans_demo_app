package com.berrakaya.mobildemoapp.feature.catalog.data

import com.berrakaya.mobildemoapp.feature.catalog.domain.CatalogRepository
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.Product
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.ProductGroup
import kotlinx.coroutines.delay
import javax.inject.Inject

class SampleCatalogRepository @Inject constructor() : CatalogRepository {

    override suspend fun getProductGroups(): List<ProductGroup> {
        delay(SIMULATED_DELAY_MS)
        return SampleCatalogData.groups.map { group ->
            group.copy(productCount = SampleCatalogData.products.count { it.groupId == group.id })
        }
    }

    override suspend fun getProductsByGroup(groupId: String): List<Product> {
        delay(SIMULATED_DELAY_MS)
        return SampleCatalogData.products.filter { it.groupId == groupId }
    }

    override suspend fun getProduct(productId: String): Product? {
        delay(SIMULATED_DELAY_MS)
        return SampleCatalogData.products.firstOrNull { it.id == productId }
    }

    private companion object {
        const val SIMULATED_DELAY_MS = 600L
    }

    override suspend fun getProductGroup(groupId: String): ProductGroup? =
        getProductGroups().firstOrNull { it.id == groupId }
}