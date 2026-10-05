
package com.berrakaya.mobildemoapp.core.locale.domain

enum class AppLanguage(val tag: String?) {
    SYSTEM(null),
    ENGLISH("en"),
    TURKISH("tr");

    companion object {
        fun fromTag(tag: String?): AppLanguage =
            entries.firstOrNull { it.tag == tag } ?: SYSTEM
    }
}