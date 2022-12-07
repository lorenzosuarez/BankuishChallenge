package com.lsuarez.bankuishchallenge.presentation.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.lsuarez.bankuishchallenge.presentation.screen.details.DetailScreen
import com.lsuarez.bankuishchallenge.presentation.screen.home.HomeScreen
import com.lsuarez.bankuishchallenge.util.Const
import com.lsuarez.domain.model.Item

/**
 * Created by Lorenzo on 12/5/2022.
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController, context: Context) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = { slideInHorizontally(animationSpec = tween(500)) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.Details.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it / 2 },
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it / 2 },
                    animationSpec = tween(500)
                )
            }
        ) {
            val repoItem = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Item>(Const.REPO_ITEM_DETAILS_ARGUMENT_KEY)

            if (repoItem != null) {
                DetailScreen(
                    repoItem = repoItem,
                    navController = navController,
                    context
                )
            }
        }
    }
}
