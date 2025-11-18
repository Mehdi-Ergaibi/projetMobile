package com.programmationmb.projet_mobile.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Cart(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment =
        Alignment.CenterHorizontally) {
        Text(text = "Cart")
    }
}