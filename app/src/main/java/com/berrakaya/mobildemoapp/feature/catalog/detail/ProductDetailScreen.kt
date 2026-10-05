package com.berrakaya.mobildemoapp.feature.catalog.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.designsystem.component.LoadStateContent
import com.berrakaya.mobildemoapp.core.designsystem.component.NexansTopAppBar
import com.berrakaya.mobildemoapp.core.designsystem.theme.Spacing
import com.berrakaya.mobildemoapp.core.ui.LoadState
import com.berrakaya.mobildemoapp.core.ui.resolve
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.Product
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.ProductSpec

@Composable
fun ProductDetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductDetailContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onRetry = viewModel::retry,
        modifier = modifier,
    )
}

@Composable
private fun ProductDetailContent(
    uiState: LoadState<Product>,
    onBackClick: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        NexansTopAppBar(
            title = if (uiState is LoadState.Success) uiState.data.code else "",
            onBackClick = onBackClick,
        )
        LoadStateContent(
            state = uiState,
            onRetry = onRetry,
            modifier = Modifier.weight(1f),
        ) { product ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(Spacing.md),
                verticalArrangement = Arrangement.spacedBy(Spacing.md),
            ) {
                Text(
                    text = product.name.resolve(),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = product.description.resolve(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = stringResource(R.string.product_specs_title),
                    style = MaterialTheme.typography.titleMedium,
                )
                SpecsTable(specs = product.specs)
            }
        }
    }
}

@Composable
private fun SpecsTable(
    specs: List<ProductSpec>,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column {
            specs.forEachIndexed { index, spec ->
                if (index > 0) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                }
                Row(modifier = Modifier.padding(Spacing.md)) {
                    Text(
                        text = spec.label.resolve(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f),
                    )
                    Text(
                        text = spec.value,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}