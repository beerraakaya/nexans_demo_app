package com.berrakaya.mobildemoapp.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berrakaya.mobildemoapp.core.locale.domain.AppLanguage
import com.berrakaya.mobildemoapp.core.locale.domain.LanguageRepository
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeMode
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val languageRepository: LanguageRepository,
    private val themeRepository: ThemeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProfileUiState(selectedLanguage = languageRepository.getLanguage())
    )
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            themeRepository.themeMode.collect { mode ->
                _uiState.update { it.copy(themeMode = mode) }
            }
        }
    }

    fun onLanguageSelected(language: AppLanguage) {
        languageRepository.setLanguage(language)
        _uiState.update { it.copy(selectedLanguage = language) }
    }

    fun onThemeModeSelected(mode: ThemeMode) {
        viewModelScope.launch { themeRepository.setThemeMode(mode) }
    }
}