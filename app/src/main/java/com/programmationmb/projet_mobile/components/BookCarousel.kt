package com.programmationmb.projet_mobile.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.programmationmb.projet_mobile.R
import kotlinx.coroutines.delay

@Composable
fun BookCarousel() {

    val books = listOf(
        R.drawable.book1,
        R.drawable.etf,
        R.drawable.psofmoney
    )

    var index by remember { mutableIntStateOf(0) }

    // Auto-slide every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            index = (index + 1) % books.size
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .height(280.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5ECE3)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        AnimatedContent(
            targetState = index,
            label = "carousel_animation",
            transitionSpec = { fadeIn() togetherWith fadeOut() }
        ) { currentIndex ->
            Image(
                painter = painterResource(id = books[currentIndex]),
                contentDescription = "Book",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .padding(top = 0.dp, bottom = 0.dp)
            )
        }
    }
}
