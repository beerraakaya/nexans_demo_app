package com.berrakaya.mobildemoapp.feature.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.designsystem.component.SingleChoiceDialog
import com.berrakaya.mobildemoapp.core.designsystem.theme.NexansTheme
import com.berrakaya.mobildemoapp.core.designsystem.theme.Spacing
import com.berrakaya.mobildemoapp.core.locale.domain.AppLanguage
import com.berrakaya.mobildemoapp.core.settings.domain.ThemeMode

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileContent(
        uiState = uiState,
        onLanguageSelected = viewModel::onLanguageSelected,
        onThemeModeSelected = viewModel::onThemeModeSelected,
        modifier = modifier,
    )
}

@Composable
private fun ProfileContent(
    uiState: ProfileUiState,
    onLanguageSelected: (AppLanguage) -> Unit,
    onThemeModeSelected: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showLanguageDialog by rememberSaveable { mutableStateOf(false) }
    var showThemeDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.md),
    ) {
        Text(
            text = stringResource(R.string.nav_profile),
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = stringResource(R.string.profile_preferences),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Surface(
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface,
        ) {
            Column {
                SettingsItem(
                    icon = Icons.Outlined.Language,
                    title = stringResource(R.string.profile_language),
                    value = uiState.selectedLanguage.displayName(),
                    onClick = { showLanguageDialog = true },
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                SettingsItem(
                    icon = Icons.Outlined.Palette,
                    title = stringResource(R.string.profile_theme),
                    value = uiState.themeMode.displayName(),
                    onClick = { showThemeDialog = true },
                )
            }
        }
    }

    if (showLanguageDialog) {
        SingleChoiceDialog(
            title = stringResource(R.string.profile_language),
            options = AppLanguage.entries,
            selected = uiState.selectedLanguage,
            optionLabel = { it.displayName() },
            onSelect = { language ->
                showLanguageDialog = false
                onLanguageSelected(language)
            },
            onDismiss = { showLanguageDialog = false },
        )
    }

    if (showThemeDialog) {
        SingleChoiceDialog(
            title = stringResource(R.string.profile_theme),
            options = ThemeMode.entries,
            selected = uiState.themeMode,
            optionLabel = { it.displayName() },
            onSelect = { mode ->
                showThemeDialog = false
                onThemeModeSelected(mode)
            },
            onDismiss = { showThemeDialog = false },
        )
    }
}

@Composable
private fun SettingsItem(
    icon: ImageVector,
    title: String,
    value: String,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        leadingContent = { Icon(imageVector = icon, contentDescription = null) },
        headlineContent = { Text(title) },
        supportingContent = { Text(value) },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfileContentPreview() {
    NexansTheme {
        ProfileContent(
            uiState = ProfileUiState(
                selectedLanguage = AppLanguage.TURKISH,
                themeMode = ThemeMode.DARK,
            ),
            onLanguageSelected = {},
            onThemeModeSelected = {},
        )
    }
}