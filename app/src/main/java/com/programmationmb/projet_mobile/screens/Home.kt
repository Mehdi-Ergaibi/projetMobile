package com.programmationmb.projet_mobile.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.programmationmb.projet_mobile.components.BookCarousel
import com.programmationmb.projet_mobile.data.AuthViewModel
import com.programmationmb.projet_mobile.navigation.Screen
import androidx.compose.ui.res.stringResource
import com.programmationmb.projet_mobile.R

@Composable
fun Home(navController: NavController, authViewModel: AuthViewModel) {
    val currentUser = authViewModel.loggedInUser.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.home_title),
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 34.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        BookCarousel()

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (currentUser.value != null) {
                    navController.navigate(Screen.AllBooks.route)
                } else {
                    navController.navigate(Screen.Login.route)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF11D494),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.get_started),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}