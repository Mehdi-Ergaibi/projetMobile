package com.programmationmb.projet_mobile.data.repository

import com.programmationmb.projet_mobile.R
import com.programmationmb.projet_mobile.data.model.Book

object BookRepository {

    private val books = listOf(
        Book(
            id = 1,
            image = null,
            title = "Atomic Habits",
            author = "James Clear",
            price = 12.99,
            bestSeller = true,
            genre = "Self-help",
            rating = 4.8,
            description = "A practical guide to building good habits, breaking bad ones, and mastering the tiny behaviors that lead to remarkable results."
        ),
        Book(
            id = 2,
            image = R.drawable.etf,
            title = "ETF Guide",
            author = "Matt Morgan",
            price = 18.50,
            bestSeller = false,
            genre = "Finance",
            rating = 4.3,
            description = "An easy-to-follow introduction to ETFs (Exchange Traded Funds), explaining how they work and how to use them to build wealth."
        ),
        Book(
            id = 3,
            image = R.drawable.psofmoney,
            title = "Psychology of Money",
            author = "Morgan Housel",
            price = 10.99,
            bestSeller = true,
            genre = "Finance",
            rating = 4.7,
            description = "Explores the emotional and psychological aspects of money, sharing timeless lessons on wealth, greed, and happiness."
        ),
        Book(
            id = 4,
            image = R.drawable.rdpd,
            title = "Rich Dad Poor Dad",
            author = "Robert Kiyosaki",
            price = 9.99,
            bestSeller = true,
            genre = "Finance",
            rating = 4.6,
            description = "A classic personal finance book contrasting two approaches to money: the mindset of the rich and the mindset of the poor."
        ),
        Book(
            id = 5,
            image = R.drawable.thels,
            title = "The Lean Startup",
            author = "Eric Ries",
            price = 13.50,
            bestSeller = false,
            genre = "Business",
            rating = 4.4,
            description = "Introduces the Lean Startup methodology for building businesses and products efficiently while reducing risks and waste."
        ),
        Book(
            id = 6,
            image = R.drawable.tfas,
            title = "Thinking, Fast and Slow",
            author = "Daniel Kahneman",
            price = 16.00,
            bestSeller = true,
            genre = "Self-help",
            rating = 4.5,
            description = "Explains the two systems of thinking in the human mind and how they influence our decisions, intuition, and judgment."
        ),
        // Additional Finance books
        Book(
            id = 7,
            image = null,
            title = "The Intelligent Investor",
            author = "Benjamin Graham",
            price = 15.99,
            bestSeller = true,
            genre = "Finance",
            rating = 4.7,
            description = "A classic book on value investing and fundamental analysis that has influenced generations of investors."
        ),
        Book(
            id = 8,
            image = null,
            title = "A Random Walk Down Wall Street",
            author = "Burton Malkiel",
            price = 14.50,
            bestSeller = true,
            genre = "Finance",
            rating = 4.4,
            description = "Argues that stock market prices evolve according to a random walk and cannot be predicted consistently."
        ),
        Book(
            id = 9,
            image = null,
            title = "The Little Book of Common Sense Investing",
            author = "John C. Bogle",
            price = 11.99,
            bestSeller = false,
            genre = "Finance",
            rating = 4.6,
            description = "Advocates for index fund investing as the most effective strategy for most individual investors."
        ),
        Book(
            id = 10,
            image = null,
            title = "Your Money or Your Life",
            author = "Vicki Robin",
            price = 13.25,
            bestSeller = true,
            genre = "Finance",
            rating = 4.5,
            description = "A transformative approach to personal finance that connects money management with life values and fulfillment."
        ),
        Book(
            id = 11,
            image = null,
            title = "The Total Money Makeover",
            author = "Dave Ramsey",
            price = 12.75,
            bestSeller = true,
            genre = "Finance",
            rating = 4.6,
            description = "A step-by-step plan for getting out of debt and building wealth through proven financial principles."
        ),
        Book(
            id = 12,
            image = null,
            title = "The Millionaire Next Door",
            author = "Thomas J. Stanley",
            price = 10.99,
            bestSeller = false,
            genre = "Finance",
            rating = 4.4,
            description = "Reveals the common traits and habits of everyday millionaires who built wealth through frugality and discipline."
        ),
        // Additional Self-help books
        Book(
            id = 13,
            image = null,
            title = "The 7 Habits of Highly Effective People",
            author = "Stephen R. Covey",
            price = 14.99,
            bestSeller = true,
            genre = "Self-help",
            rating = 4.7,
            description = "A holistic approach to personal and professional effectiveness through principle-centered living."
        ),
        Book(
            id = 14,
            image = null,
            title = "How to Win Friends and Influence People",
            author = "Dale Carnegie",
            price = 9.99,
            bestSeller = true,
            genre = "Self-help",
            rating = 4.6,
            description = "Timeless advice on building relationships, communicating effectively, and becoming a more influential person."
        ),
        Book(
            id = 15,
            image = null,
            title = "The Power of Now",
            author = "Eckhart Tolle",
            price = 11.50,
            bestSeller = true,
            genre = "Self-help",
            rating = 4.5,
            description = "A guide to spiritual enlightenment that emphasizes the importance of living in the present moment."
        ),
        Book(
            id = 16,
            image = null,
            title = "Daring Greatly",
            author = "Brené Brown",
            price = 13.25,
            bestSeller = false,
            genre = "Self-help",
            rating = 4.4,
            description = "Explores the power of vulnerability and how it can transform the way we live, love, and lead."
        ),
        Book(
            id = 17,
            image = null,
            title = "The Subtle Art of Not Giving a F*ck",
            author = "Mark Manson",
            price = 12.99,
            bestSeller = true,
            genre = "Self-help",
            rating = 4.3,
            description = "A counterintuitive approach to living a good life by learning to prioritize what truly matters."
        ),
        Book(
            id = 18,
            image = null,
            title = "Mindset: The New Psychology of Success",
            author = "Carol S. Dweck",
            price = 11.75,
            bestSeller = true,
            genre = "Self-help",
            rating = 4.5,
            description = "Explains how adopting a growth mindset can lead to greater achievement and personal development."
        ),
        // Additional Business books
        Book(
            id = 19,
            image = null,
            title = "Good to Great",
            author = "Jim Collins",
            price = 15.99,
            bestSeller = true,
            genre = "Business",
            rating = 4.6,
            description = "Research-based insights into how companies can make the leap from good performance to great results."
        ),
        Book(
            id = 20,
            image = null,
            title = "The Innovator's Dilemma",
            author = "Clayton Christensen",
            price = 14.25,
            bestSeller = false,
            genre = "Business",
            rating = 4.4,
            description = "Explores why successful companies often fail to innovate and how to avoid this common pitfall."
        )
    )

    fun getAllBooks(): List<Book> = books

    fun getBestSellers(): List<Book> =
        books.filter { it.bestSeller }

    fun getBookById(id: Int): Book {
        return books.first { it.id == id }
    }


    fun getBooksByGenre(genre: String, excludeId: Int? = null): List<Book> =
        books.filter { it.genre == genre && it.id != excludeId }
}
