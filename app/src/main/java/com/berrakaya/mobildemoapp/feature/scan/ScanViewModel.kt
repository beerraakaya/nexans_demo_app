package com.berrakaya.mobildemoapp.feature.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berrakaya.mobildemoapp.core.ui.LoadState
import com.berrakaya.mobildemoapp.core.ui.loadCatching
import com.berrakaya.mobildemoapp.feature.catalog.domain.CatalogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface ScanState {
    data object Scanning : ScanState
    data object Searching : ScanState
    data class ProductFound(val productId: String) : ScanState
    data class NotFound(val barcode: String) : ScanState
}

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<ScanState>(ScanState.Scanning)
    val state: StateFlow<ScanState> = _state.asStateFlow()

    fun onBarcodeDetected(barcode: String) {
        if (_state.value != ScanState.Scanning) return
        _state.value = ScanState.Searching

        viewModelScope.launch {
            val result = loadCatching { catalogRepository.findProductByBarcode(barcode) }
            _state.value = when (result) {
                is LoadState.Success ->
                    result.data?.let { ScanState.ProductFound(it.id) }
                        ?: ScanState.NotFound(barcode)

                else -> ScanState.NotFound(barcode)
            }
        }
    }

    fun onRescan() {
        _state.value = ScanState.Scanning
    }
}