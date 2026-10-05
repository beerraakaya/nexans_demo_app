package com.berrakaya.mobildemoapp.feature.catalog.di

import com.berrakaya.mobildemoapp.feature.catalog.data.SampleCatalogRepository
import com.berrakaya.mobildemoapp.feature.catalog.domain.CatalogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogModule {

    @Binds
    @Singleton
    abstract fun bindCatalogRepository(
        impl: SampleCatalogRepository,
    ): CatalogRepository
}