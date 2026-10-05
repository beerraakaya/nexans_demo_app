package com.berrakaya.mobildemoapp.feature.profile

import com.berrakaya.mobildemoapp.core.locale.domain.AppLanguage
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeMode

data class ProfileUiState(
    val selectedLanguage: AppLanguage = AppLanguage.SYSTEM,
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
)