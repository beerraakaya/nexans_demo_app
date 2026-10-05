package com.berrakaya.mobildemoapp.feature.profile

import com.berrakaya.mobildemoapp.core.locale.domain.AppLanguage
import com.berrakaya.mobildemoapp.core.locale.domain.LanguageRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileViewModelTest {

    private class FakeLanguageRepository(
        var current: AppLanguage = AppLanguage.SYSTEM,
    ) : LanguageRepository {
        override fun getLanguage() = current
        override fun setLanguage(language: AppLanguage) {
            current = language
        }
    }

    @Test
    fun `initial state reflects saved language`() {
        val viewModel = ProfileViewModel(FakeLanguageRepository(AppLanguage.TURKISH))

        assertEquals(AppLanguage.TURKISH, viewModel.uiState.value.selectedLanguage)
    }

    @Test
    fun `selecting a language updates repository and state`() {
        val repository = FakeLanguageRepository()
        val viewModel = ProfileViewModel(repository)

        viewModel.onLanguageSelected(AppLanguage.ENGLISH)

        assertEquals(AppLanguage.ENGLISH, repository.current)
        assertEquals(AppLanguage.ENGLISH, viewModel.uiState.value.selectedLanguage)
    }
}