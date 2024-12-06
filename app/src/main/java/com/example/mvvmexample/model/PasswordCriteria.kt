package com.example.mvvmexample.model

data class PasswordCriteria(
    val hasMinLength: Boolean = false,
    val hasUpperCase: Boolean = false,
    val hasNumber: Boolean = false,
    val hasSpecialChar: Boolean = false
)
