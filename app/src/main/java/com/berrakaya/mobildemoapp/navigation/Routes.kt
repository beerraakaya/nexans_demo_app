package com.berrakaya.mobildemoapp.navigation

import kotlinx.serialization.Serializable

sealed interface TopLevelRoute

@Serializable
data object HomeRoute : TopLevelRoute

@Serializable
data object CatalogGraph : TopLevelRoute

@Serializable
data object ToolsRoute : TopLevelRoute

@Serializable
data object ProfileRoute : TopLevelRoute

// Catalog graph destinations
@Serializable
data object CatalogRoute

@Serializable
data class ProductListRoute(val groupId: String)

@Serializable
data class ProductDetailRoute(val productId: String)

@Serializable
data object ScanRoute