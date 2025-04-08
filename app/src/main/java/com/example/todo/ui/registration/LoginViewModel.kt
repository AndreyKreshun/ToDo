package com.example.todo.ui.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    // Состояние для email
    var email by mutableStateOf("")
        private set

    // Состояние для пароля
    var password by mutableStateOf("")
        private set

    // Состояние для ошибки email
    var emailError by mutableStateOf<String?>(null)
        private set

    // Состояние для ошибки пароля
    var passwordError by mutableStateOf<String?>(null)
        private set

    // Состояние загрузки
    var isLoading by mutableStateOf(false)
        private set

    // Обновление email
    fun updateEmail(newEmail: String) {
        email = newEmail
        if (emailError != null) {
            emailError = null
        }
    }

    // Обновление пароля
    fun updatePassword(newPassword: String) {
        password = newPassword
        if (passwordError != null) {
            passwordError = null
        }
    }

    // Валидация полей
    private fun validate(): Boolean {
        var isValid = true

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
        } else if (password.length < 6) {
            passwordError = "Password should be at least 6 characters"
            isValid = false
        }

        return isValid
    }

    // Обработка логина
    fun login(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (!validate()) return

        isLoading = true

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading = false
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Login failed")
                }
            }
    }

}