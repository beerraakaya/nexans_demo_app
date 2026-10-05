package com.berrakaya.mobildemoapp.feature.catalog.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.berrakaya.mobildemoapp.core.ui.LoadState
import com.berrakaya.mobildemoapp.core.ui.loadCatching
import com.berrakaya.mobildemoapp.feature.catalog.domain.CatalogRepository
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.Product
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.ProductGroup
import com.berrakaya.mobildemoapp.navigation.ProductListRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductListData(
    val group: ProductGroup,
    val products: List<Product>,
)

@HiltViewModel
class ProductListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val catalogRepository: CatalogRepository,
) : ViewModel() {

    private val groupId = savedStateHandle.toRoute<ProductListRoute>().groupId

    private val _uiState = MutableStateFlow<LoadState<ProductListData>>(LoadState.Loading)
    val uiState: StateFlow<LoadState<ProductListData>> = _uiState.asStateFlow()

    private var loadJob: Job? = null

    init {
        load()
    }

    fun retry() = load()

    private fun load() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _uiState.value = LoadState.Loading
            _uiState.value = loadCatching {
                coroutineScope {
                    val group = async { catalogRepository.getProductGroup(groupId) }
                    val products = async { catalogRepository.getProductsByGroup(groupId) }
                    ProductListData(
                        group = checkNotNull(group.await()),
                        products = products.await(),
                    )
                }
            }
        }
    }
}