package com.berrakaya.mobildemoapp.core.settings.di

import com.berrakaya.mobildemoapp.core.settings.data.DataStoreThemeRepository
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    @Singleton
    abstract fun bindThemeRepository(impl: DataStoreThemeRepository): ThemeRepository
}