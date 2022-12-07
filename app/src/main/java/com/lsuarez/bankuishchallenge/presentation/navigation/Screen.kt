package com.lsuarez.bankuishchallenge.presentation.navigation

/**
 * Created by Lorenzo on 12/5/2022.
 */

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{itemId}") {
        fun passItemId(itemId: String) = "details_screen/$itemId"
    }
}