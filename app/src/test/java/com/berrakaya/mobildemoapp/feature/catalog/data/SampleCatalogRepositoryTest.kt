package com.berrakaya.mobildemoapp.feature.catalog.data

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SampleCatalogRepositoryTest {

    private val repository = SampleCatalogRepository()

    @Test
    fun `search matches product code ignoring case`() = runTest {
        val results = repository.searchProducts("nym")

        assertEquals(listOf("nym-j"), results.map { it.id })
    }

    @Test
    fun `search matches names in every language`() = runTest {
        val results = repository.searchProducts("halojensiz")

        assertEquals(listOf("nhxmh-j"), results.map { it.id })
    }

    @Test
    fun `search returns empty list for unknown query`() = runTest {
        assertTrue(repository.searchProducts("xyz123").isEmpty())
    }
}