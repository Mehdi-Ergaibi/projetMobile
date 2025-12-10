package com.programmationmb.projet_mobile.data.model

data class Book(
    val id: Int,
    val title: String,
    val description: String,
    val genre: String,
    val price: Double,
    val imageUrl: String?,
    val rating: Double,
    val ratingCount: Int = 0,
    val bestSeller: Boolean = false,
    val quantity: Int = 1
) {
    fun withQuantity(newQuantity: Int): Book = copy(quantity = newQuantity)
}
