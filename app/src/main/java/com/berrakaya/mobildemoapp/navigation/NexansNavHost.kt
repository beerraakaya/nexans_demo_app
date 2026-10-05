package com.berrakaya.mobildemoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.berrakaya.mobildemoapp.feature.catalog.CatalogScreen
import com.berrakaya.mobildemoapp.feature.home.HomeScreen
import com.berrakaya.mobildemoapp.feature.profile.ProfileScreen
import com.berrakaya.mobildemoapp.feature.tools.ToolsScreen

@Composable
fun NexansNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        composable<HomeRoute> { HomeScreen() }
        composable<CatalogRoute> { CatalogScreen(onGroupClick = { /* 5C'de ürün listesine bağlanacak */ }) }
        composable<ToolsRoute> { ToolsScreen() }
        composable<ProfileRoute> { ProfileScreen() }
    }
}