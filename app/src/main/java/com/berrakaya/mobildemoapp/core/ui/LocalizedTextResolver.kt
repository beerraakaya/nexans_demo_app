package com.berrakaya.mobildemoapp.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import com.berrakaya.mobildemoapp.core.model.LocalizedText

@Composable
@ReadOnlyComposable
fun LocalizedText.resolve(): String =
    get(LocalConfiguration.current.locales[0].language)