package com.programmationmb.projet_mobile.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.programmationmb.projet_mobile.data.AuthViewModel
import com.programmationmb.projet_mobile.navigation.Screen

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf("") }
    val uiState = authViewModel.uiState.collectAsState()

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            // Top X icon
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

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Create Account",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

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

            if (localError.isNotEmpty()) {
                Text(localError, color = Color.Red)
            }

            uiState.value.error?.let { Text(it, color = Color.Red) }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (password != confirmPassword) {
                        localError = "Passwords do not match"
                        return@Button
                    }
                    if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        localError = "All fields are required"
                        return@Button
                    }
                    localError = ""
                    authViewModel.register(name, email, password) {
                        navController.navigate(Screen.Login.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF11D494),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Sign Up")
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                "Join our community of readers now",
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.reddit.com"))
                        context.startActivity(intent)
                    }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already have an account? ", color = Color.Gray)
            Text(
                "Login",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { navController.navigate(Screen.Login.route) }
            )
        }
    }
}
