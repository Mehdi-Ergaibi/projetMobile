package com.programmationmb.projet_mobile.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmationmb.projet_mobile.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class AuthUiState(
    val loading: Boolean = false,
    val error: String? = null
)

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val loggedInUser = userRepository.loggedInUser.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(loading = true)
            val result = userRepository.login(email, password)
            _uiState.value = result.fold(
                onSuccess = { AuthUiState(loading = false, error = null) },
                onFailure = { AuthUiState(loading = false, error = it.message) }
            )
            if (result.isSuccess) onSuccess()
        }
    }

    fun register(name: String, email: String, password: String, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(loading = true)
            val result = userRepository.register(name, email, password)
            _uiState.value = result.fold(
                onSuccess = { AuthUiState(loading = false, error = null) },
                onFailure = { AuthUiState(loading = false, error = it.message) }
            )
            if (result.isSuccess) onSuccess()
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }
}
