package com.berrakaya.mobildemoapp.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.locale.domain.AppLanguage
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeMode

@Composable
internal fun AppLanguage.displayName(): String = when (this) {
    AppLanguage.SYSTEM -> stringResource(R.string.language_system)
    AppLanguage.ENGLISH -> stringResource(R.string.language_english)
    AppLanguage.TURKISH -> stringResource(R.string.language_turkish)
}

@Composable
internal fun ThemeMode.displayName(): String = when (this) {
    ThemeMode.SYSTEM -> stringResource(R.string.theme_system)
    ThemeMode.LIGHT -> stringResource(R.string.theme_light)
    ThemeMode.DARK -> stringResource(R.string.theme_dark)
}