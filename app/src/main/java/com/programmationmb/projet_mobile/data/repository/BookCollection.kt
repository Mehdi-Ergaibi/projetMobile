package com.programmationmb.projet_mobile.data.repository

import com.programmationmb.projet_mobile.data.model.Book

class BookCollection(private val books: List<Book>) : Iterable<Book> {

    override fun iterator(): Iterator<Book> {
        return BookIterator(books)
    }

    private class BookIterator(private val books: List<Book>) : Iterator<Book> {

        private var index = 0

        override fun hasNext(): Boolean {
            return index < books.size
        }

        override fun next(): Book {
            if (!hasNext()) throw NoSuchElementException()
            return books[index++]
        }
    }
}
