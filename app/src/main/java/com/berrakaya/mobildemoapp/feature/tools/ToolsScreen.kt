package com.berrakaya.mobildemoapp.feature.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.designsystem.component.PlaceholderContent

@Composable
fun ToolsScreen(modifier: Modifier = Modifier) {
    PlaceholderContent(
        title = stringResource(R.string.nav_tools),
        modifier = modifier,
    )
}
