package com.programmationmb.projet_mobile.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.res.stringResource
import com.programmationmb.projet_mobile.data.CartViewModel
import com.programmationmb.projet_mobile.data.ProductViewModel
import com.programmationmb.projet_mobile.data.model.Book
import com.programmationmb.projet_mobile.notifications.NotificationHelper
import com.programmationmb.projet_mobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    bookId: Int,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val productsState = productViewModel.uiState.collectAsState()
    var book by remember { mutableStateOf<Book?>(null) }

    LaunchedEffect(bookId) {
        book = productViewModel.getBook(bookId)
    }

    val currentBook = book
    val similarBooks = productsState.value.products.filter { it.genre == currentBook?.genre && it.id != bookId }

    if (currentBook == null) {
        LoadingState(onBack)
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        cartViewModel.addToCart(currentBook)
                        NotificationHelper.showCartAdded(context, currentBook.title)
                        Toast.makeText(
                            context,
                            context.getString(R.string.added_to_cart_toast),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF11D494),
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Add to Cart",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            AsyncImage(
                model = currentBook.imageUrl,
                contentDescription = currentBook.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.DarkGray)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = currentBook.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = currentBook.genre,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                RatingSection(currentBook)

                Spacer(Modifier.height(24.dp))

                Text(
                    text = stringResource(id = R.string.description_label),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = currentBook.description.ifEmpty { "No description available for this book." },
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    lineHeight = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(32.dp))

                if (similarBooks.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.similar_books),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        similarBooks.forEach { similarBook ->
                            SimilarBookItem(book = similarBook)
                            Spacer(Modifier.width(12.dp))
                        }
                    }
                }

                Spacer(Modifier.height(80.dp))
            }
        }
    }
}

@Composable
private fun RatingSection(book: Book) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(0.4f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = book.rating.toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(4.dp))

            Row {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (index < book.rating.toInt()) Color.White else Color.Gray.copy(alpha = 0.5f),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "${book.ratingCount} reviews",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Column(
            modifier = Modifier.weight(0.6f)
        ) {
            val percentages = listOf(50, 30, 10, 5, 5)

            (5 downTo 1).forEachIndexed { index, star ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = star.toString(),
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.width(20.dp)
                    )

                    Spacer(Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .height(6.dp)
                            .weight(1f)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF293933))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(percentages[index] / 100f)
                                .background(Color.White)
                        )
                    }

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "${percentages[index]}%",
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.width(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingState(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Loading...", color = Color.White)
        Spacer(Modifier.height(8.dp))
        Button(onClick = onBack) {
            Text("Back")
        }
    }
}

@Composable
private fun SimilarBookItem(book: Book) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .clickable { }
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.DarkGray)
        ) {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = book.title,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = book.title,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}