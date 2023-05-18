package com.vyatsu.uhtamobile.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vyatsu.uhtamobile.models.AppDatabase
import com.vyatsu.uhtamobile.models.Device
import com.vyatsu.uhtamobile.models.api.AuthApiRepository
import com.vyatsu.uhtamobile.models.api.DeviceApiRepository
import com.vyatsu.uhtamobile.models.repository.DeviceRepository
import com.vyatsu.uhtamobile.models.repository.EmployeeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UhtaViewModel : ViewModel() {
    private val deviceApiRepository = DeviceApiRepository()
    private val authApiRepository = AuthApiRepository()
    private lateinit var employeeRepository: EmployeeRepository
    private lateinit var deviceRepository: DeviceRepository
    private val _uiState = MutableStateFlow(UhtaUiState())
    val uiState = _uiState.asStateFlow()
    fun loadDatabase(appContext: Context) {
        val db = AppDatabase.getDataBase(appContext)
        employeeRepository = EmployeeRepository(db.employeeDao())
        deviceRepository = DeviceRepository(db.deviceDao())
        loadData()
    }

    private fun loadData() {
        val employees = employeeRepository.getAllEmployee()
        val employee = if (employees.isEmpty()) {
            null
        } else {
            employees[0]
        }
        val devices = deviceRepository.getAllDevices()
        _uiState.update {
            UhtaUiState(employee, DevicesUiState(devices), true)
        }
    }

    suspend fun login(login: String, password: String) {
        val res = authApiRepository.authEmployee(login, password)
        if (res != null)
            employeeRepository.insertEmployee(res)
        _uiState.update {
            it.updateEmployee(res)
        }
    }

    fun logout() {
        if (_uiState.value.employee != null)
            employeeRepository.deleteEmployee(_uiState.value.employee!!)
        _uiState.update { UhtaUiState(null, DevicesUiState(), true) }
    }

    suspend fun loadDevices() {
        deviceRepository.deleteDevices()
        val res = deviceApiRepository.fetchDevices()
        deviceRepository.insertDevices(*res.toTypedArray())
        _uiState.update { it.copy(devicesUiState = DevicesUiState(res)) }
    }

    fun selectDevice(device: Device?) {
        _uiState.update { UhtaUiState(it.employee, DevicesUiState(it.devicesUiState.devices, device), it.isLoaded) }
    }

    suspend fun saveDevice(device: Device) {
        if (deviceApiRepository.saveDevice(device) == null) return
        loadDevices()
    }
}