package com.lsuarez.bankuishchallenge.presentation.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Lorenzo on 12/7/2022.
 */

@Composable
fun ErrorSnackBar(
    message: String,
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(snackbarHostState) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long
            )
        }
    }
}