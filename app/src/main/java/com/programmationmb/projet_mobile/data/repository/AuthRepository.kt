package com.programmationmb.projet_mobile.data.repository

data class User(
    val name: String,
    val email: String,
    val password: String
)

object AuthRepository {
    val users = mutableListOf<User>()

    init {
        users.add(User(
            name = "mehdi",
            email = "ergaibi.mhdi@gmail.com",
            password = "1234"
        ))
    }

    fun register(user: User): Boolean {
        if (users.any { it.email == user.email }) {
            return false
        }
        users.add(user)
        return true
    }

    fun login(email: String, password: String): User? {
        return users.find { it.email == email && it.password == password }
    }
}
