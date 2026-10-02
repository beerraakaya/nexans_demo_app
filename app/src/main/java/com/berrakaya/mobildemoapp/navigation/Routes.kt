package com.berrakaya.mobildemoapp.navigation

import kotlinx.serialization.Serializable

sealed interface TopLevelRoute

@Serializable
data object HomeRoute : TopLevelRoute

@Serializable
data object CatalogRoute : TopLevelRoute

@Serializable
data object ToolsRoute : TopLevelRoute

@Serializable
data object ProfileRoute : TopLevelRoute