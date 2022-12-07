package com.lsuarez.bankuishchallenge.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity

/**
 * Created by Lorenzo on 12/7/2022.
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GoBackUpFloatingButton(
    modifier: Modifier,
    isVisibleBecauseOfScrolling: Boolean,
    onBackUpClick: () -> Unit
) {
    LocalDensity.current
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisibleBecauseOfScrolling,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            onClick = { onBackUpClick() }
        ) {
            Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "Go to top")
        }
    }
}