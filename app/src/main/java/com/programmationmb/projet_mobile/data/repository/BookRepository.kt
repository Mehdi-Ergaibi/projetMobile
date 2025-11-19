package com.programmationmb.projet_mobile.data.repository

import com.programmationmb.projet_mobile.R
import com.programmationmb.projet_mobile.data.model.Book

object BookRepository {

    private val books = listOf(
        Book(null,"Atomic Habits", "James Clear", 12.99, true),
        Book(R.drawable.etf, "ETF Guide", "Matt Morgan", 18.50, false),
        Book(R.drawable.psofmoney, "Psychology of Money", "Morgan Housel", 10.99, true),
        Book(R.drawable.rdpd, "Rich Dad Poor Dad", "Robert Kiyosaki", 9.99, true),
        Book(R.drawable.thels, "The Lean Startup", "Eric Ries", 13.50, false),
        Book(R.drawable.tfas, "Thinking, Fast and Slow", "Daniel Kahneman", 16.00, true)
    )

    fun getAllBooks(): BookCollection = BookCollection(books)

    fun getBestSellers(): BookCollection =
        BookCollection(books.filter { it.bestSeller })
}
