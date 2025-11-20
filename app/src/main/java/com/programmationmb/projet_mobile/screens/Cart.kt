package com.programmationmb.projet_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.programmationmb.projet_mobile.components.CartManager
import com.programmationmb.projet_mobile.data.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    val cartItems by CartManager.cartItems.collectAsState()

    val totalPrice by remember(cartItems) {
        derivedStateOf {
            cartItems.sumOf { it.price * it.quantity }
        }
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .height(64.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(8.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                }

                Text(
                    text = "Cart",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },

        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("$${"%.2f".format(totalPrice)}", color = Color.White)
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF11D494),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Checkout")
                }
            }
        }
    ) { padding ->

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text("Your cart is empty", color = Color.White)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                items(
                    items = cartItems,
                    key = { it.id }
                ) { book ->
                    CartItem(book = book)
                }
                item { Spacer(Modifier.height(100.dp)) }
            }
        }
    }
}

@Composable
fun CartItem(book: Book) {
    val cartItems by CartManager.cartItems.collectAsState()

    val currentBook = cartItems.find { it.id == book.id }

    if (currentBook == null) {
        return
    }

    val currentQuantity = currentBook.quantity

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .background(Color.Black)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                if (book.image != null) {
                    Image(
                        painter = painterResource(book.image),
                        contentDescription = book.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Text("No Image", color = Color.Black, fontSize = 10.sp)
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = book.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 2
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "$${book.price}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Quantity: $currentQuantity",
                color = Color.White,
                fontSize = 14.sp
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                Button(
                    onClick = {
                        if (currentQuantity > 1) {
                            CartManager.updateQuantity(book.id, currentQuantity - 1)
                        } else {
                            CartManager.updateQuantity(book.id, 0)
                        }
                    },
                    modifier = Modifier.size(30.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF293933),
                        contentColor = Color.White
                    )
                ) {
                    Text("-", fontSize = 16.sp)
                }

                Text(
                    text = currentQuantity.toString(),
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                Button(
                    onClick = {
                        CartManager.updateQuantity(book.id, currentQuantity + 1)
                    },
                    modifier = Modifier.size(30.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF293933),
                        contentColor = Color.White
                    )
                ) {
                    Text("+", fontSize = 16.sp)
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)
    }
}