package com.berrakaya.mobildemoapp.core.ui

import kotlin.coroutines.cancellation.CancellationException

sealed interface LoadState<out T> {
    data object Loading : LoadState<Nothing>
    data class Success<T>(val data: T) : LoadState<T>
    data object Error : LoadState<Nothing>
}

suspend fun <T> loadCatching(block: suspend () -> T): LoadState<T> =
    try {
        LoadState.Success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        LoadState.Error
    }