package com.vyatsu.uhtamobile.viewmodels

import androidx.lifecycle.ViewModel
import com.vyatsu.uhtamobile.models.Device
import com.vyatsu.uhtamobile.models.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UhtaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UhtaUiState())
    val uiState = _uiState.asStateFlow()

    fun login(login: String, password: String){
        if (login.trim() != "admin")
            return

        _uiState.update {
            it.updateEmployee(Employee(1, "admin", "Ведерников", "Иван", "Дмитриевич"))
        }
    }

    fun logout(){
        _uiState.update { it.updateEmployee(null) }
    }

    fun selectDevice(device: Device?){
        _uiState.update { it.updateDeviceUiState(it.devicesUiState.selectDevice(device)) }
    }

    fun saveDevice(device: Device){
    }
}