package com.berrakaya.mobildemoapp.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.designsystem.component.PlaceholderContent

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    PlaceholderContent(
        title = stringResource(R.string.nav_profile),
        modifier = modifier,
    )
}