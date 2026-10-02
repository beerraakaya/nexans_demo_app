package com.berrakaya.mobildemoapp.feature.catalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.designsystem.component.PlaceholderContent

@Composable
fun CatalogScreen(modifier: Modifier = Modifier) {
    PlaceholderContent(
        title = stringResource(R.string.nav_catalog),
        modifier = modifier,
    )
}