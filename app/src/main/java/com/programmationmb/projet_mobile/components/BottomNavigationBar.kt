package com.programmationmb.projet_mobile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.programmationmb.projet_mobile.R
import com.programmationmb.projet_mobile.data.NavigationItem
import com.programmationmb.projet_mobile.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(R.string.home),
            icon = Icons.Default.Home,
            route = Screen.Home.route
        ),
        NavigationItem(
            title = stringResource(R.string.all_books),
            icon = Icons.Default.Search,
            route = Screen.AllBooks.route
        ),
        NavigationItem(
            title = stringResource(R.string.cart),
            icon = Icons.Default.ShoppingCart,
            route = Screen.Cart.route
        ),
        NavigationItem(
            title = stringResource(R.string.profile),
            icon = Icons.Default.Person,
            route = Screen.Profile.route
        )
    )

    NavigationBar(
        containerColor = Color(0xFF192223) // your color
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.route,
                    )
                },
                label = {
                    Text(text = item.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,

                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.Gray,

                    indicatorColor = Color.Transparent
                )

            )
        }
    }
}
