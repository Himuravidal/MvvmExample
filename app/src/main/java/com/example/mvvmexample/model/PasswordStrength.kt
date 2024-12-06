package com.example.mvvmexample.model

import android.graphics.Color

enum class PasswordStrength {
    WEAK,
    MEDIUM,
    STRONG,
    VERY_STRONG;

    fun getColor(): Int {
        return when (this) {
            WEAK -> Color.RED
            MEDIUM -> Color.YELLOW
            STRONG -> Color.GREEN
            VERY_STRONG -> Color.parseColor("#FF00C853")
        }
    }
}
