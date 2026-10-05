package com.berrakaya.mobildemoapp.feature.catalog.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.designsystem.component.LoadStateContent
import com.berrakaya.mobildemoapp.core.designsystem.component.NexansTopAppBar
import com.berrakaya.mobildemoapp.core.designsystem.theme.Spacing
import com.berrakaya.mobildemoapp.core.ui.LoadState
import com.berrakaya.mobildemoapp.core.ui.resolve

@Composable
fun ProductListScreen(
    onBackClick: () -> Unit,
    onProductClick: (productId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductListContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onProductClick = onProductClick,
        onRetry = viewModel::retry,
        modifier = modifier,
    )
}

@Composable
private fun ProductListContent(
    uiState: LoadState<ProductListData>,
    onBackClick: () -> Unit,
    onProductClick: (String) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        NexansTopAppBar(
            title = if (uiState is LoadState.Success) uiState.data.group.name.resolve() else "",
            onBackClick = onBackClick,
        )
        LoadStateContent(
            state = uiState,
            onRetry = onRetry,
            modifier = Modifier.weight(1f),
        ) { data ->
            if (data.products.isEmpty()) {
                EmptyProducts()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(Spacing.md),
                    verticalArrangement = Arrangement.spacedBy(Spacing.sm),
                ) {
                    items(items = data.products, key = { it.id }) { product ->
                        ProductCard(product = product, onClick = { onProductClick(product.id) })
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyProducts() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.lg),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.product_list_empty),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}