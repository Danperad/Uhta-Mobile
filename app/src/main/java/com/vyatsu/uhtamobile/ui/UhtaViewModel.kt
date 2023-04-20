package com.vyatsu.uhtamobile.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UhtaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UhtaUiState())
    val uiState = _uiState.asStateFlow()

    fun auth(login: String, password: String) : Boolean{
        TODO()
    }
}