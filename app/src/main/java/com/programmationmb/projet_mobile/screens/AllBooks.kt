package com.programmationmb.projet_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.navigation.NavController
import com.programmationmb.projet_mobile.data.model.Book
import com.programmationmb.projet_mobile.data.repository.BookRepository
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.unit.DpOffset


@Composable
fun AllBooks(navController: NavController) {

    val bookList = BookRepository.getAllBooks()
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp) // background is from theme
    ) {

        // ----------------- TOP BAR -------------------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // optional height
            contentAlignment = Alignment.CenterStart
        ) {
            // Centered title
            Text(
                text = "BookStore",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center) // center in Box
            )

            // Cart icon on the right
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterEnd) // right side
                    .padding(end = 12.dp)
                    .size(28.dp)
                    .clickable {
                        navController.navigate("cart")
                    }
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        // ----------------- CUSTOM SEARCH BAR -------------------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF293933))
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.weight(1f)) {
                    if (searchText.isEmpty()) {
                        Text(
                            text = "Search books...",
                            color = Color.Gray
                        )
                    }
                    BasicTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            color = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ----------------- FILTERS -------------------
        Row(
            modifier = Modifier.fillMaxWidth(),
            //horizontalArrangement = Arrangement.SpaceBetween

        ) {
            FilterBox("Genre")
            Spacer(modifier = Modifier.width(8.dp))
            FilterBox("Price")
            Spacer(modifier = Modifier.width(8.dp))
            FilterBox("Rating")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ----------------- BOOK LIST -------------------
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(bookList) { book ->
                BookItem(book)
            }
        }
    }
}


// ----------------- FILTER BOX -------------------
@Composable
fun FilterRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start // no space between
    ) {
        FilterBox("Genre")
        Spacer(modifier = Modifier.width(8.dp)) // small gap between boxes
        FilterBox("Price")
        Spacer(modifier = Modifier.width(8.dp))
        FilterBox("Rating")
    }
}

@Composable
fun FilterBox(title: String) {
    var expanded by remember { mutableStateOf(false) }

    // Wrap clickable filter in a Box
    Box {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF293933))
                .clickable { expanded = true }
                .padding(horizontal = 10.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, fontWeight = FontWeight.SemiBold, color = Color.White)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = 0.dp, y = 0.dp), // dropdown directly below the Box
            modifier = Modifier.background(Color(0xFF293933))
        ) {
            val options = when (title) {
                "Genre" -> listOf("Self-help", "Finance", "Fiction", "Business")
                "Price" -> listOf("Low to High", "High to Low")
                "Rating" -> listOf("5 Stars", "4 Stars", "3 Stars")
                else -> listOf()
            }

            options.forEach {
                DropdownMenuItem(
                    text = { Text(it, color = Color.White) },
                    onClick = { expanded = false }
                )
            }
        }
    }
}



// ----------------- BOOK ITEM -------------------
@Composable
fun BookItem(book: Book) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF1c2624))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically // center everything vertically
    ) {
        // LEFT COLUMN: Texts and Price
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            if (book.bestSeller) {
                Text(
                    text = "BestSeller",
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(book.title, fontWeight = FontWeight.Bold, color = Color.White)
            Text(book.author, color = Color.Gray)

            Spacer(modifier = Modifier.height(6.dp))

            // Price Box
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF293933)) // price box color
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text("\$${book.price}", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // RIGHT COLUMN: Image in container
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFEBEBEB)),
            contentAlignment = Alignment.Center // center the image inside the box
        ) {
            Image(
                painter = painterResource(id = book.image),
                contentDescription = book.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(80.dp) // keep some padding inside container
            )
        }
    }
}

