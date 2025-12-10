package com.programmationmb.projet_mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.res.stringResource
import com.programmationmb.projet_mobile.R
import com.programmationmb.projet_mobile.data.CartViewModel
import com.programmationmb.projet_mobile.data.repository.CartItemUi
import android.widget.Toast

@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel) {
    val cartItems = cartViewModel.cartItems.collectAsState()
    val total = cartViewModel.total.collectAsState()
    val context = LocalContext.current

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
                text = stringResource(id = R.string.cart),
                    color = Color.White,
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
                    Text("Total", color = Color.White)
                    Text("$${"%.2f".format(total.value)}", color = Color.White)
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        cartViewModel.clear()
                        Toast.makeText(
                            context,
                            context.getString(R.string.checkout_success),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF11D494),
                        contentColor = Color.Black
                    )
                ) {
                    Text(stringResource(id = R.string.checkout))
                }
            }
        }
    ) { padding ->

        if (cartItems.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(id = R.string.cart_empty), color = Color.White)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                items(
                    items = cartItems.value,
                    key = { it.book.id }
                ) { item ->
                    CartItem(item = item, cartViewModel = cartViewModel)
                }
                item { Spacer(Modifier.height(100.dp)) }
            }
        }
    }
}

@Composable
fun CartItem(item: CartItemUi, cartViewModel: CartViewModel) {
    val currentQuantity = item.quantity

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
                AsyncImage(
                    model = item.book.imageUrl,
                    contentDescription = item.book.title,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.book.title,
                    fontSize = 16.sp,
                    color = Color.White,
                    maxLines = 2
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "$${item.book.price}",
                    fontSize = 15.sp,
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
                        cartViewModel.updateQuantity(item.book.id, currentQuantity - 1)
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
                        cartViewModel.updateQuantity(item.book.id, currentQuantity + 1)
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