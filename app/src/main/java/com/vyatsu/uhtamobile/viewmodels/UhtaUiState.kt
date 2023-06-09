package com.vyatsu.uhtamobile.viewmodels

import com.vyatsu.uhtamobile.models.Employee

data class UhtaUiState(
    val employee: Employee? = null,
    val devicesUiState: DevicesUiState,
    val isLoaded: Boolean = false
){
    constructor(): this(devicesUiState = DevicesUiState())
    fun updateEmployee(newEmployee: Employee?) : UhtaUiState{
        return this.copy(employee = newEmployee)
    }
    fun updateDeviceUiState(devicesUiState: DevicesUiState): UhtaUiState{
        return this.copy(devicesUiState = devicesUiState)
    }
    fun load(): UhtaUiState{
        return this.copy(isLoaded = true)
    }
}
