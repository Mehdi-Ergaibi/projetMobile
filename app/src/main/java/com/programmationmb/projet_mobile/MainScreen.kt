package com.programmationmb.projet_mobile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.programmationmb.projet_mobile.components.BottomNavigationBar
import com.programmationmb.projet_mobile.navigation.Screen
import com.programmationmb.projet_mobile.screens.AllBooks
import com.programmationmb.projet_mobile.screens.Cart
import com.programmationmb.projet_mobile.screens.Home
import com.programmationmb.projet_mobile.screens.Profile

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController ,
            startDestination = Screen.Home.route ,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable (Screen.Home.route) {
                Home(navController)
            }
            composable(Screen.AllBooks.route) {
                AllBooks(navController)
            }
            composable(Screen.Cart.route) {
                Cart(navController)
            }
            composable(Screen.Profile.route) {
                Profile(navController)
            }
        }
    }
}