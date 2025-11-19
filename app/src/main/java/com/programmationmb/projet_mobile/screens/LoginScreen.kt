package com.programmationmb.projet_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.programmationmb.projet_mobile.data.AuthViewModel
import androidx.compose.ui.res.painterResource
import com.programmationmb.projet_mobile.R
import androidx.compose.ui.unit.sp
import com.programmationmb.projet_mobile.navigation.Screen

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // -------------------- MAIN FORM --------------------
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top X icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    "✕",
                    color = Color.White,
                    modifier = Modifier.clickable { navController.navigate(Screen.Home.route) }
                        .padding(4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Title
            Text(
                "Login",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Email input
            Text("Email", color = Color.Gray)
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF293933), RoundedCornerShape(10.dp))
                    .padding(12.dp),
                decorationBox = { inner -> inner() }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password input with eye icon
            Text("Password", color = Color.Gray)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF293933), RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                // Eye icon
                val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                Image(
                    imageVector = icon,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { passwordVisible = !passwordVisible }
                        .padding(4.dp)
                        .height(24.dp)
                        .width(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Forgot password
            Text(
                "Forgot password?",
                color = Color.LightGray,
                modifier = Modifier.clickable { }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Error message
            if (authViewModel.errorMessage.value.isNotEmpty()) {
                Text(
                    authViewModel.errorMessage.value,
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Login button full width
            Button(
                onClick = {
                    val success = authViewModel.login(email, password)
                    if (success) {
                        navController.navigate(Screen.Home.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF11D494),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                Text("Login")
            }
        }

        // -------------------- REGISTER LINK AT BOTTOM --------------------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have an account? ", color = Color.Gray)
            Text(
                "Register",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { navController.navigate(Screen.Register.route) }
            )
        }
    }
}
