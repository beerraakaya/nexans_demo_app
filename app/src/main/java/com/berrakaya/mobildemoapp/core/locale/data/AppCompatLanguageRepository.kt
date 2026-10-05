package com.berrakaya.mobildemoapp.core.locale.data

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.berrakaya.mobildemoapp.core.locale.domain.AppLanguage
import com.berrakaya.mobildemoapp.core.locale.domain.LanguageRepository

class AppCompatLanguageRepository : LanguageRepository {

    override fun getLanguage(): AppLanguage =
        AppLanguage.fromTag(AppCompatDelegate.getApplicationLocales()[0]?.language) //Kullanıcının seçtiği dili döndürür.

    override fun setLanguage(language: AppLanguage) {
        val locales = language.tag
            ?.let(LocaleListCompat::forLanguageTags)
            ?: LocaleListCompat.getEmptyLocaleList()
        AppCompatDelegate.setApplicationLocales(locales) //Kullanıcının seçtiği dili uygulamaya uygular.
    }
}