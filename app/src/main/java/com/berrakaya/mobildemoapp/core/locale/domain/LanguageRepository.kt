package com.berrakaya.mobildemoapp.core.locale.domain

interface LanguageRepository {
    fun getLanguage(): AppLanguage
    fun setLanguage(language: AppLanguage)
}