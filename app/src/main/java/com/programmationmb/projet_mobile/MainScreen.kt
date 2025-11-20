package com.programmationmb.projet_mobile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.programmationmb.projet_mobile.components.BottomNavigationBar
import com.programmationmb.projet_mobile.data.AuthViewModel
import com.programmationmb.projet_mobile.data.repository.BookRepository
import com.programmationmb.projet_mobile.navigation.Screen
import com.programmationmb.projet_mobile.screens.AllBooks
import com.programmationmb.projet_mobile.screens.CartScreen
import com.programmationmb.projet_mobile.screens.Home
import com.programmationmb.projet_mobile.screens.LoginScreen
import com.programmationmb.projet_mobile.screens.Profile
import com.programmationmb.projet_mobile.screens.RegisterScreen
import com.programmationmb.projet_mobile.ui.screens.BookDetailsScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel ()

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
                Home(navController, authViewModel)
            }
            composable(Screen.AllBooks.route) {
                AllBooks(navController)
            }
            composable(Screen.Cart.route) {
                CartScreen(navController)
            }
            composable(Screen.Profile.route) {
                Profile(navController, authViewModel)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController, authViewModel)
            }

            composable(Screen.Register.route) {
                RegisterScreen(navController, authViewModel)
            }

            composable("bookDetails/{bookId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("bookId")?.toInt() ?: 0
                val book = BookRepository.getBookById(id)

                BookDetailsScreen(
                    book = book,
                    onBack = { navController.popBackStack() }
                )
            }



        }
    }
}