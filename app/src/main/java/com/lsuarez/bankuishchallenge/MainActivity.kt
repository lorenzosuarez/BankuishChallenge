package com.lsuarez.bankuishchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.lsuarez.bankuishchallenge.presentation.navigation.NavGraph
import com.lsuarez.bankuishchallenge.ui.theme.BankuishChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankuishChallengeTheme {
                val navController = rememberAnimatedNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
