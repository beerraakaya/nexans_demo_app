package com.berrakaya.mobildemoapp.feature.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.designsystem.component.LoadStateContent
import com.berrakaya.mobildemoapp.core.designsystem.theme.NexansTheme
import com.berrakaya.mobildemoapp.core.designsystem.theme.Spacing
import com.berrakaya.mobildemoapp.core.model.LocalizedText
import com.berrakaya.mobildemoapp.core.ui.LoadState
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.ProductGroup

@Composable
fun CatalogScreen(
    onGroupClick: (groupId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatalogViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CatalogContent(
        uiState = uiState,
        onGroupClick = onGroupClick,
        onRetry = viewModel::retry,
        modifier = modifier,
    )
}

@Composable
private fun CatalogContent(
    uiState: LoadState<List<ProductGroup>>,
    onGroupClick: (String) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LoadStateContent(state = uiState, onRetry = onRetry, modifier = modifier) { groups ->
        LazyColumn(
            contentPadding = PaddingValues(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
        ) {
            item {
                CatalogHeader(modifier = Modifier.padding(bottom = Spacing.sm))
            }
            items(items = groups, key = { it.id }) { group ->
                ProductGroupCard(group = group, onClick = { onGroupClick(group.id) })
            }
        }
    }
}

@Composable
private fun CatalogHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.nav_catalog),
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = stringResource(R.string.catalog_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CatalogContentPreview() {
    val groups = listOf(
        ProductGroup(
            id = "grid",
            name = LocalizedText(mapOf("en" to "Power Grid Cables")),
            description = LocalizedText(mapOf("en" to "Medium voltage cables for distribution networks")),
            productCount = 2,
        ),
        ProductGroup(
            id = "building",
            name = LocalizedText(mapOf("en" to "Building Wires")),
            description = LocalizedText(mapOf("en" to "Low voltage cables for buildings")),
            productCount = 1,
        ),
    )
    NexansTheme {
        CatalogContent(
            uiState = LoadState.Success(groups),
            onGroupClick = {},
            onRetry = {},
        )
    }
}