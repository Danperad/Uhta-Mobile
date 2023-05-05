package com.vyatsu.uhtamobile.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vyatsu.uhtamobile.models.AppDatabase
import com.vyatsu.uhtamobile.models.Device
import com.vyatsu.uhtamobile.models.Employee
import com.vyatsu.uhtamobile.models.repository.DeviceRepository
import com.vyatsu.uhtamobile.models.repository.EmployeeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UhtaViewModel(appContext: Context) : ViewModel() {
    private val employeeRepository: EmployeeRepository
    private val deviceRepository: DeviceRepository
    private val _uiState = MutableStateFlow(UhtaUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val db = AppDatabase.getDataBase(appContext)
        employeeRepository = EmployeeRepository(db.employeeDao())
        deviceRepository = DeviceRepository(db.deviceDao())
    }
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