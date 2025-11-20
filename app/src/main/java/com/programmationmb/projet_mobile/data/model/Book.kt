package com.programmationmb.projet_mobile.data.model


data class Book(
    val id: Int,
    val image: Int?,
    val title: String,
    val description: String,
    val author: String,
    val price: Double,
    val bestSeller: Boolean,
    val rating: Double,
    val genre: String,
    var quantity: Int = 1
)
