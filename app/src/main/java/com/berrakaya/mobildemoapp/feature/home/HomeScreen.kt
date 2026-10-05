package com.berrakaya.mobildemoapp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.designsystem.theme.Spacing
import com.berrakaya.mobildemoapp.feature.catalog.list.ProductCard

private val PopularSearches = listOf("NYM-J", "XLPE", "Fiber", "Cat6")

@Composable
fun HomeScreen(
    onProductClick: (productId: String) -> Unit,
    onBrowseCatalogClick: () -> Unit,
    onScanClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val searchState by viewModel.searchState.collectAsStateWithLifecycle()

    HomeContent(
        query = query,
        searchState = searchState,
        onQueryChange = viewModel::onQueryChange,
        onProductClick = onProductClick,
        onBrowseCatalogClick = onBrowseCatalogClick,
        onScanClick = onScanClick,
        modifier = modifier,
    )
}

@Composable
private fun HomeContent(
    query: String,
    searchState: SearchState,
    onQueryChange: (String) -> Unit,
    onProductClick: (String) -> Unit,
    onBrowseCatalogClick: () -> Unit,
    onScanClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Spacing.md),
    ) {
        Spacer(modifier = Modifier.size(Spacing.md))
        Text(
            text = stringResource(R.string.home_title),
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = stringResource(R.string.home_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.size(Spacing.md))
        SearchField(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = { keyboardController?.hide() },
        )
        Spacer(modifier = Modifier.size(Spacing.md))

        Box(modifier = Modifier.weight(1f)) {
            when (searchState) {
                SearchState.Idle -> IdleContent(
                    onSuggestionClick = onQueryChange,
                    onBrowseCatalogClick = onBrowseCatalogClick,
                    onScanClick = onScanClick,
                )

                SearchState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                )

                SearchState.Error -> CenteredMessage(stringResource(R.string.state_error_title))
                is SearchState.Results -> if (searchState.products.isEmpty()) {
                    CenteredMessage(stringResource(R.string.search_no_results, query.trim()))
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = Spacing.md),
                        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
                    ) {
                        items(items = searchState.products, key = { it.id }) { product ->
                            ProductCard(
                                product = product,
                                onClick = {
                                    keyboardController?.hide()
                                    onProductClick(product.id)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(stringResource(R.string.search_placeholder)) },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.search_clear),
                    )
                }
            }
        },
        singleLine = true,
        shape = MaterialTheme.shapes.extraLarge,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IdleContent(
    onSuggestionClick: (String) -> Unit,
    onBrowseCatalogClick: () -> Unit,
    onScanClick: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.md)) {
        // ... popüler aramalar aynı kalıyor ...
        ActionCard(
            icon = Icons.Outlined.QrCodeScanner,
            title = stringResource(R.string.home_scan),
            description = stringResource(R.string.home_scan_desc),
            onClick = onScanClick,
        )
        ActionCard(
            icon = Icons.Outlined.Category,
            title = stringResource(R.string.home_browse_catalog),
            description = stringResource(R.string.home_browse_catalog_desc),
            onClick = onBrowseCatalogClick,
        )
    }
}

@Composable
private fun ActionCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier.padding(Spacing.md),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
            Spacer(modifier = Modifier.width(Spacing.md))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun CenteredMessage(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.lg),
        contentAlignment = Alignment.TopCenter,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}