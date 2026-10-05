package com.berrakaya.mobildemoapp.feature.catalog.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.berrakaya.mobildemoapp.core.ui.LoadState
import com.berrakaya.mobildemoapp.core.ui.loadCatching
import com.berrakaya.mobildemoapp.feature.catalog.domain.CatalogRepository
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.Product
import com.berrakaya.mobildemoapp.navigation.ProductDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val catalogRepository: CatalogRepository,
) : ViewModel() {

    private val productId = savedStateHandle.toRoute<ProductDetailRoute>().productId

    private val _uiState = MutableStateFlow<LoadState<Product>>(LoadState.Loading)
    val uiState: StateFlow<LoadState<Product>> = _uiState.asStateFlow()

    private var loadJob: Job? = null

    init {
        load()
    }

    fun retry() = load()

    private fun load() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _uiState.value = LoadState.Loading
            _uiState.value = loadCatching { checkNotNull(catalogRepository.getProduct(productId)) }
        }
    }
}