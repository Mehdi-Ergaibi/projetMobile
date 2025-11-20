package com.programmationmb.projet_mobile.components

import com.programmationmb.projet_mobile.data.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object CartManager {
    private val _cartItems = MutableStateFlow<List<Book>>(emptyList())
    val cartItems: StateFlow<List<Book>> = _cartItems.asStateFlow()

    fun addToCart(book: Book) {
        val currentList = _cartItems.value.toMutableList()
        val existingBook = currentList.find { it.id == book.id }

        if (existingBook != null) {
            val updatedList = currentList.map {
                if (it.id == book.id) it.copy(quantity = it.quantity + 1)
                else it
            }
            _cartItems.value = updatedList
        } else {
            val newBook = book.copy(quantity = 1)
            _cartItems.value = currentList + newBook
        }
    }

    fun updateQuantity(bookId: Int, newQuantity: Int) {
        val currentList = _cartItems.value
        if (newQuantity <= 0) {
            _cartItems.value = currentList.filter { it.id != bookId }
        } else {
            _cartItems.value = currentList.map {
                if (it.id == bookId) it.copy(quantity = newQuantity)
                else it
            }
        }
    }

    fun removeFromCart(bookId: Int) {
        val currentList = _cartItems.value
        _cartItems.value = currentList.filter { it.id != bookId }
    }

    fun getTotalPrice(): Double {
        return _cartItems.value.sumOf { it.price * it.quantity }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}