package com.berrakaya.mobildemoapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.berrakaya.mobildemoapp.navigation.NexansBottomBar
import com.berrakaya.mobildemoapp.navigation.NexansNavHost
import com.berrakaya.mobildemoapp.navigation.ScanRoute
import com.berrakaya.mobildemoapp.navigation.navigateToTopLevel

@Composable
fun NexansApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry?.destination?.hasRoute(ScanRoute::class) != true

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NexansBottomBar(
                    currentDestination = backStackEntry?.destination,
                    onNavigate = { destination -> navController.navigateToTopLevel(destination.route) },
                )
            }
        },
    ) { innerPadding ->
        NexansNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}