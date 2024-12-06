package com.example.mvvmexample

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.example.mvvmexample.databinding.ActivityMainBinding
import com.example.mvvmexample.model.PasswordCriteria
import com.example.mvvmexample.model.PasswordStrength
import com.example.mvvmexample.presentation.PasswordViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupTextListener()
        setupObservers()
    }

    private fun setupTextListener() {
        binding.passwordInput.addTextChangedListener {
            viewModel.validatePassword(it?.toString() ?: "")
        }
    }

    private fun setupObservers() {
        viewModel.passwordStrengthLiveData.observe(this) { strength ->
            updateStrengthIndicator(strength)
        }

        viewModel.passwordCriteria.observe(this) { criteria ->
            updateCriteriaIndicators(criteria)
        }

    }

    private fun updateStrengthIndicator(strength: PasswordStrength) {
        binding.apply {
            strengthIndicator.setBackgroundColor(strength.getColor())
            strengthText.text = strength.name
            strengthText.setTextColor(strength.getColor())
        }
    }

    private fun updateCriteriaIndicators(criteria: PasswordCriteria) {
        binding.apply {
            lengthCheckmark.isVisible = criteria.hasMinLength
            uppercaseCheckmark.isVisible = criteria.hasUpperCase
            numberCheckmark.isVisible = criteria.hasNumber
            specialCharCheckmark.isVisible = criteria.hasSpecialChar

            lengthCriteria.setTextColor(if (criteria.hasMinLength)
                Color.GREEN else Color.GRAY)
            uppercaseCriteria.setTextColor(if (criteria.hasUpperCase)
                Color.GREEN else Color.GRAY)
            numberCriteria.setTextColor(if (criteria.hasNumber)
                Color.GREEN else Color.GRAY)
            specialCharCriteria.setTextColor(if (criteria.hasSpecialChar)
                Color.GREEN else Color.GRAY)
        }
    }
}