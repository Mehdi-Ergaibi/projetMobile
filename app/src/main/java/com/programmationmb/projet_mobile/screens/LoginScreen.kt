package com.programmationmb.projet_mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.programmationmb.projet_mobile.data.AuthViewModel
import com.programmationmb.projet_mobile.data.STATIC_EMAIL
import com.programmationmb.projet_mobile.data.STATIC_NAME
import com.programmationmb.projet_mobile.data.STATIC_PASSWORD
import com.programmationmb.projet_mobile.navigation.Screen

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // -------------------- TOP BAR (X ICON) --------------------
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                "✕",
                color = Color.White,
                modifier = Modifier
                    .clickable { navController.navigate(Screen.Home.route) }
                    .padding(4.dp),
                fontWeight = FontWeight.Bold
            )
        }

        // -------------------- TITLE --------------------
        Text(
            "Login",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )

        Spacer(Modifier.height(20.dp))

        // -------------------- EMAIL --------------------
        Text("Email", color = Color.Gray)
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF293933), RoundedCornerShape(10.dp))
                .padding(12.dp),
            decorationBox = { inner -> inner() }
        )

        Spacer(Modifier.height(12.dp))

        // -------------------- PASSWORD --------------------
        Text("Password", color = Color.Gray)
        BasicTextField(
            value = password,
            onValueChange = { password = it },
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF293933), RoundedCornerShape(10.dp))
                .padding(12.dp),
            decorationBox = { inner -> inner() }
        )

        Spacer(Modifier.height(6.dp))

        // -------------------- FORGOT PASSWORD --------------------
        Text(
            "Forgot password?",
            color = Color.LightGray,
            modifier = Modifier.clickable { }
        )

        Spacer(Modifier.height(10.dp))

        // -------------------- ERROR MESSAGE --------------------
        if (authViewModel.errorMessage.value.isNotEmpty()) {
            Text(
                authViewModel.errorMessage.value,
                color = Color.Red
            )
        }

        // -------------------- LOGIN BUTTON --------------------

        Button(onClick = {
            val success = authViewModel.login(email, password)
            if (success) {
                navController.navigate(Screen.Home.route)
            }
        }) {
            Text("Login")
        }

        Spacer(Modifier.height(20.dp))

        // -------------------- REGISTER LINK --------------------
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have an account? ",
                color = Color.Gray)

            Text(
                "Register",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
    }
}
