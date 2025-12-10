package com.programmationmb.projet_mobile.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.programmationmb.projet_mobile.data.AuthViewModel
import com.programmationmb.projet_mobile.navigation.Screen

@Composable
fun Profile(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val userState = authViewModel.loggedInUser.collectAsState()
    val isLoggedIn = userState.value != null
    val username = userState.value?.name

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Profile",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(30.dp))

        if (isLoggedIn) {
            Text(
                text = "Hello, $username ",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(40.dp))

            Button(
                onClick = {
                    // Reset state and go home
                    authViewModel.logout()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Profile.route) { inclusive = true }
                    }
                }
            ) {
                Text("Logout")
            }

        } else {
            Text(
                text = "You must log in",
                color = Color.Gray
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Go to Login",
                color = Color(0xFF64B5F6),
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
    }
}
