package com.berrakaya.mobildemoapp.core.model

data class LocalizedText(private val translations: Map<String, String>) {

    init {
        require(DEFAULT_LANGUAGE in translations) {
            "LocalizedText must contain a '$DEFAULT_LANGUAGE' translation"
        }
    }

    fun get(languageTag: String): String =
        translations[languageTag] ?: translations.getValue(DEFAULT_LANGUAGE)

    private companion object {
        const val DEFAULT_LANGUAGE = "en"
    }
}