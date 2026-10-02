package com.berrakaya.mobildemoapp.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.berrakaya.mobildemoapp.R

enum class TopLevelDestination(
    val route: TopLevelRoute,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val labelRes: Int,
) {
    HOME(HomeRoute, Icons.Filled.Home, Icons.Outlined.Home, R.string.nav_home),
    CATALOG(CatalogRoute, Icons.Filled.Category, Icons.Outlined.Category, R.string.nav_catalog),
    TOOLS(ToolsRoute, Icons.Filled.Build, Icons.Outlined.Build, R.string.nav_tools),
    PROFILE(ProfileRoute, Icons.Filled.Person, Icons.Outlined.Person, R.string.nav_profile),
}