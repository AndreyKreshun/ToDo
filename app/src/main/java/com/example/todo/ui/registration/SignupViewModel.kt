package com.example.todo.ui.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    // Состояния для полей формы
    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var acceptedTerms by mutableStateOf(false)
        private set

    // Состояния ошибок валидации
    var firstNameError by mutableStateOf<String?>(null)
        private set

    var lastNameError by mutableStateOf<String?>(null)
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var termsError by mutableStateOf<String?>(null)
        private set

    // Состояние загрузки
    var isLoading by mutableStateOf(false)
        private set

    // Обновление состояний
    fun updateFirstName(newValue: String) {
        firstName = newValue
        if (firstNameError != null) firstNameError = null
    }

    fun updateLastName(newValue: String) {
        lastName = newValue
        if (lastNameError != null) lastNameError = null
    }

    fun updateEmail(newValue: String) {
        email = newValue
        if (emailError != null) emailError = null
    }

    fun updatePassword(newValue: String) {
        password = newValue
        if (passwordError != null) passwordError = null
    }

    fun toggleTermsAccepted() {
        acceptedTerms = !acceptedTerms
        if (termsError != null) termsError = null
    }

    // Валидация формы
    private fun validate(): Boolean {
        var isValid = true

        if (firstName.isBlank()) {
            firstNameError = "First name cannot be empty"
            isValid = false
        }

        if (lastName.isBlank()) {
            lastNameError = "Last name cannot be empty"
            isValid = false
        }

        if (email.isBlank()) {
            emailError = "Email cannot be empty"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Please enter a valid email"
            isValid = false
        }

        if (password.isBlank()) {
            passwordError = "Password cannot be empty"
            isValid = false
        } else if (password.length < 8) {
            passwordError = "Password must be at least 8 characters"
            isValid = false
        }

        if (!acceptedTerms) {
            termsError = "You must accept the terms"
            isValid = false
        }

        return isValid
    }

    // Логика регистрации
    fun signUp(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (!validate()) return

        viewModelScope.launch {
            isLoading = true
            try {
                // Здесь должна быть логика регистрации
                // Например, вызов repository.signUp(firstName, lastName, email, password)

                // Имитация сетевого запроса
                kotlinx.coroutines.delay(1500)

                // Если успешно
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Registration failed")
            } finally {
                isLoading = false
            }
        }
    }
}