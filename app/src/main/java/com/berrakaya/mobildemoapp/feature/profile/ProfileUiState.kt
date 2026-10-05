package com.berrakaya.mobildemoapp.feature.profile

import com.berrakaya.mobildemoapp.core.locale.domain.AppLanguage

data class ProfileUiState(
    val selectedLanguage: AppLanguage = AppLanguage.SYSTEM,
)