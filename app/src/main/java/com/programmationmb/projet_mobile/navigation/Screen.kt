package com.programmationmb.projet_mobile.navigation

sealed class Screen( val route: String) {
    object Home : Screen("home")
    object AllBooks : Screen("all_books")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")
}