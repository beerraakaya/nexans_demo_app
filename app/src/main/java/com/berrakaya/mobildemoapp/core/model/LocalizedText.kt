package com.berrakaya.mobildemoapp.core.model

data class LocalizedText(private val translations: Map<String, String>) {

    init {
        require(DEFAULT_LANGUAGE in translations) {
            "LocalizedText must contain a '$DEFAULT_LANGUAGE' translation"
        }
    }

    fun get(languageTag: String): String =
        translations[languageTag] ?: translations.getValue(DEFAULT_LANGUAGE)

    fun matches(query: String): Boolean =
        translations.values.any { it.contains(query, ignoreCase = true) }

    private companion object {
        const val DEFAULT_LANGUAGE = "en"
    }
}