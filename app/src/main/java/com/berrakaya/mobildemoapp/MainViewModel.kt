package com.berrakaya.mobildemoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeMode
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    themeRepository: ThemeRepository,
) : ViewModel() {

    val themeMode: StateFlow<ThemeMode?> = themeRepository.themeMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )
}