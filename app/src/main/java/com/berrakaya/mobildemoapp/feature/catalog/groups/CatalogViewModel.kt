package com.berrakaya.mobildemoapp.feature.catalog.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berrakaya.mobildemoapp.core.ui.LoadState
import com.berrakaya.mobildemoapp.core.ui.loadCatching
import com.berrakaya.mobildemoapp.feature.catalog.domain.CatalogRepository
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.ProductGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoadState<List<ProductGroup>>>(LoadState.Loading)
    val uiState: StateFlow<LoadState<List<ProductGroup>>> = _uiState.asStateFlow()

    private var loadJob: Job? = null

    init {
        load()
    }

    fun retry() = load()

    private fun load() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _uiState.value = LoadState.Loading
            _uiState.value = loadCatching { catalogRepository.getProductGroups() }
        }
    }
}