package com.berrakaya.mobildemoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.berrakaya.mobildemoapp.feature.catalog.detail.ProductDetailScreen
import com.berrakaya.mobildemoapp.feature.catalog.groups.CatalogScreen
import com.berrakaya.mobildemoapp.feature.catalog.list.ProductListScreen
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

        navigation<CatalogGraph>(startDestination = CatalogRoute) {
            composable<CatalogRoute> {
                CatalogScreen(
                    onGroupClick = { groupId -> navController.navigate(ProductListRoute(groupId)) },
                )
            }
            composable<ProductListRoute> {
                ProductListScreen(
                    onBackClick = { navController.navigateUp() },
                    onProductClick = { productId ->
                        navController.navigate(ProductDetailRoute(productId))
                    },
                )
            }
            composable<ProductDetailRoute> {
                ProductDetailScreen(onBackClick = { navController.navigateUp() })
            }
        }

        composable<ToolsRoute> { ToolsScreen() }
        composable<ProfileRoute> { ProfileScreen() }
    }
}