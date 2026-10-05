package com.berrakaya.mobildemoapp.core.locale.di

import com.berrakaya.mobildemoapp.core.locale.data.AppCompatLanguageRepository
import com.berrakaya.mobildemoapp.core.locale.domain.LanguageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocaleModule {

    @Binds
    @Singleton
    abstract fun bindLanguageRepository(
        impl: AppCompatLanguageRepository,
    ): LanguageRepository
}