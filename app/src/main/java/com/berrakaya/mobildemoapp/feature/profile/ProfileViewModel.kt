package com.berrakaya.mobildemoapp.feature.profile

import androidx.lifecycle.ViewModel
import com.berrakaya.mobildemoapp.core.locale.domain.AppLanguage
import com.berrakaya.mobildemoapp.core.locale.domain.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val languageRepository: LanguageRepository,
) : ViewModel()  {

    private val _uiState = MutableStateFlow(
        ProfileUiState(selectedLanguage = languageRepository.getLanguage())
    )
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onLanguageSelected(language: AppLanguage) {
        languageRepository.setLanguage(language)
        _uiState.update { it.copy(selectedLanguage = language) }
    }
}