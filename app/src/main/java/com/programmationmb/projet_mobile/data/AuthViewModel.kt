package com.programmationmb.projet_mobile.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.programmationmb.projet_mobile.data.repository.AuthRepository
import com.programmationmb.projet_mobile.data.repository.User

class AuthViewModel : ViewModel() {

    val isLoggedIn = mutableStateOf(false)
    val currentUser = mutableStateOf<User?>(null)
    val errorMessage = mutableStateOf("")

    fun login(email: String, password: String): Boolean {
        val user = AuthRepository.login(email, password)
        return if (user != null) {
            currentUser.value = user
            isLoggedIn.value = true
            errorMessage.value = ""
            true
        } else {
            errorMessage.value = "Wrong email or password"
            false
        }
    }

    fun register(name: String, email: String, password: String): Boolean {
        val success = AuthRepository.register(
            User(name, email, password)
        )

        if (!success) {
            errorMessage.value = "Email already exists"
        } else {
            errorMessage.value = ""
        }

        return success
    }

    fun logout() {
        isLoggedIn.value = false
        currentUser.value = null
    }
}
