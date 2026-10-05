package com.berrakaya.mobildemoapp.feature.profile

import com.berrakaya.mobildemoapp.core.locale.domain.AppLanguage
import com.berrakaya.mobildemoapp.core.locale.domain.LanguageRepository
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeMode
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeRepository
import com.berrakaya.mobildemoapp.testing.MainDispatcherRule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ProfileViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val languageRepository = FakeLanguageRepository()
    private val themeRepository = FakeThemeRepository()

    private fun createViewModel() = ProfileViewModel(languageRepository, themeRepository)

    @Test
    fun `initial state reflects saved language`() {
        languageRepository.current = AppLanguage.TURKISH

        val viewModel = createViewModel()

        assertEquals(AppLanguage.TURKISH, viewModel.uiState.value.selectedLanguage)
    }

    @Test
    fun `selecting a language updates repository and state`() {
        val viewModel = createViewModel()

        viewModel.onLanguageSelected(AppLanguage.ENGLISH)

        assertEquals(AppLanguage.ENGLISH, languageRepository.current)
        assertEquals(AppLanguage.ENGLISH, viewModel.uiState.value.selectedLanguage)
    }

    @Test
    fun `selecting a theme mode saves it and updates state`() {
        val viewModel = createViewModel()

        viewModel.onThemeModeSelected(ThemeMode.DARK)

        assertEquals(ThemeMode.DARK, themeRepository.current.value)
        assertEquals(ThemeMode.DARK, viewModel.uiState.value.themeMode)
    }

    private class FakeLanguageRepository(
        var current: AppLanguage = AppLanguage.SYSTEM,
    ) : LanguageRepository {
        override fun getLanguage() = current
        override fun setLanguage(language: AppLanguage) {
            current = language
        }
    }

    private class FakeThemeRepository : ThemeRepository {
        val current = MutableStateFlow(ThemeMode.SYSTEM)
        override val themeMode: Flow<ThemeMode> = current
        override suspend fun setThemeMode(mode: ThemeMode) {
            current.value = mode
        }
    }
}