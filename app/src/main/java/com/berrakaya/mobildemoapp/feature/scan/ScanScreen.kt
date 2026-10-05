package com.berrakaya.mobildemoapp.feature.scan

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.berrakaya.mobildemoapp.R
import com.berrakaya.mobildemoapp.core.designsystem.component.NexansTopAppBar
import com.berrakaya.mobildemoapp.core.designsystem.theme.Spacing

@Composable
fun ScanScreen(
    onBackClick: () -> Unit,
    onProductFound: (productId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ScanViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    var hasPermission by remember { mutableStateOf(context.hasCameraPermission()) }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted -> hasPermission = granted }

    LaunchedEffect(Unit) {
        if (!hasPermission) permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    LifecycleResumeEffect(Unit) {
        hasPermission = context.hasCameraPermission()
        onPauseOrDispose {}
    }

    LaunchedEffect(state) {
        (state as? ScanState.ProductFound)?.let { onProductFound(it.productId) }
    }

    Column(modifier = modifier.fillMaxSize()) {
        NexansTopAppBar(title = stringResource(R.string.scan_title), onBackClick = onBackClick)
        Box(modifier = Modifier.weight(1f)) {
            if (hasPermission) {
                CameraPreview(
                    onBarcodeDetected = viewModel::onBarcodeDetected,
                    modifier = Modifier.fillMaxSize(),
                )
                ScanOverlay(state = state, onRescan = viewModel::onRescan)
            } else {
                PermissionRequired(
                    onAllowClick = { permissionLauncher.launch(Manifest.permission.CAMERA) },
                    onOpenSettingsClick = { context.openAppSettings() },
                )
            }
        }
    }
}

@Composable
private fun ScanOverlay(
    state: ScanState,
    onRescan: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(260.dp)
                .border(width = 3.dp, color = Color.White, shape = MaterialTheme.shapes.large),
        )
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(Spacing.md),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface,
        ) {
            Column(
                modifier = Modifier.padding(Spacing.md),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Spacing.sm),
            ) {
                when (state) {
                    ScanState.Scanning -> Text(
                        text = stringResource(R.string.scan_hint),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                    )

                    ScanState.Searching, is ScanState.ProductFound -> CircularProgressIndicator()
                    is ScanState.NotFound -> {
                        Text(
                            text = stringResource(R.string.scan_not_found, state.barcode),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                        )
                        Button(onClick = onRescan) {
                            Text(stringResource(R.string.scan_again))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PermissionRequired(
    onAllowClick: () -> Unit,
    onOpenSettingsClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.sm, Alignment.CenterVertically),
    ) {
        Text(
            text = stringResource(R.string.scan_permission_title),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = stringResource(R.string.scan_permission_message),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
        Button(onClick = onAllowClick) {
            Text(stringResource(R.string.scan_permission_allow))
        }
        TextButton(onClick = onOpenSettingsClick) {
            Text(stringResource(R.string.scan_permission_settings))
        }
    }
}

private fun Context.hasCameraPermission(): Boolean =
    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED

private fun Context.openAppSettings() {
    startActivity(
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null),
        ),
    )
}