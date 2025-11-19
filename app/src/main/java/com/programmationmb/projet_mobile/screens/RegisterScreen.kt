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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.programmationmb.projet_mobile.data.AuthViewModel
import com.programmationmb.projet_mobile.data.STATIC_EMAIL
import com.programmationmb.projet_mobile.navigation.Screen

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf("") } // local errors (password mismatch)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                "✕",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { navController.navigate(Screen.Home.route) }
                    .padding(4.dp)
            )
        }

        Text(
            "Create Account",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(20.dp))

        // ----- Input Component -----
        @Composable
        fun inputBox(value: String, onChange: (String) -> Unit, placeholder: String) {
            BasicTextField(
                value = value,
                onValueChange = onChange,
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF293933), RoundedCornerShape(10.dp))
                    .padding(12.dp),
                decorationBox = { inner ->
                    if (value.isEmpty()) Text(placeholder, color = Color.Gray)
                    inner()
                }
            )
        }

        inputBox(name, { name = it }, "Name")
        Spacer(Modifier.height(12.dp))

        inputBox(email, { email = it }, "Email")
        Spacer(Modifier.height(12.dp))

        inputBox(password, { password = it }, "Password")
        Spacer(Modifier.height(12.dp))

        inputBox(confirmPassword, { confirmPassword = it }, "Confirm Password")
        Spacer(Modifier.height(12.dp))

        // ERROR TEXTS
        if (localError.isNotEmpty()) {
            Text(localError, color = Color.Red)
        }

        if (authViewModel.errorMessage.value.isNotEmpty()) {
            Text(authViewModel.errorMessage.value, color = Color.Red)
        }

        Spacer(Modifier.height(6.dp))

        // ----- Register Button -----
        Button(onClick = {

            // Local validation first
            if (password != confirmPassword) {
                localError = "Passwords do not match"
                return@Button
            }

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                localError = "All fields are required"
                return@Button
            }

            localError = "" // clear error

            val success = authViewModel.register(name, email, password)

            if (success) {
                navController.navigate(Screen.Login.route)
            }

        }) {
            Text("Sign Up")
        }

        Spacer(Modifier.height(14.dp))

        Text(
            "Join our community of readers now",
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(28.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already have an account? ", color = Color.Gray)

            Text(
                "Login",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
    }
}
