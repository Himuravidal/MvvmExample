package com.example.mvvmexample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmexample.model.PasswordCriteria
import com.example.mvvmexample.model.PasswordStrength

class PasswordViewModel : ViewModel() {

    private val _passwordStrengthLiveData = MutableLiveData<PasswordStrength>()
    val passwordStrengthLiveData: LiveData<PasswordStrength> = _passwordStrengthLiveData

    private val _passwordCriteria = MutableLiveData<PasswordCriteria>()
    val passwordCriteria: LiveData<PasswordCriteria> = _passwordCriteria

    fun validatePassword(password: String) {
        val criteria = PasswordCriteria(
            hasMinLength = password.length >= 8,
            hasUpperCase = password.any { it.isUpperCase() },
            hasNumber = password.any { it.isDigit() },
            hasSpecialChar = password.any { !it.isLetterOrDigit() }
        )
        _passwordCriteria.value = criteria

        _passwordStrengthLiveData.value = when {
            password.isEmpty() -> PasswordStrength.WEAK
            criteria.hasMinLength && criteria.hasUpperCase && criteria.hasNumber
                    && criteria.hasSpecialChar -> PasswordStrength.VERY_STRONG
            criteria.hasMinLength && criteria.hasUpperCase &&
                    (criteria.hasNumber || criteria.hasSpecialChar) -> PasswordStrength.STRONG
            criteria.hasMinLength &&
                    (criteria.hasUpperCase || criteria.hasNumber) -> PasswordStrength.MEDIUM
            else -> PasswordStrength.WEAK
        }

    }

}