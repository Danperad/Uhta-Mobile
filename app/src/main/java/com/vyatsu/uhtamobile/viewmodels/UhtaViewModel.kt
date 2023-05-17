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

class UhtaViewModel(appContext: Context) : ViewModel() {
    private val deviceApiRepository = DeviceApiRepository()
    private val authApiRepository = AuthApiRepository()
    private val employeeRepository: EmployeeRepository
    private val deviceRepository: DeviceRepository
    private val _uiState = MutableStateFlow(UhtaUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val db = AppDatabase.getDataBase(appContext)
        employeeRepository = EmployeeRepository(db.employeeDao())
        deviceRepository = DeviceRepository(db.deviceDao())
    }
    suspend fun login(login: String, password: String){
        val res = authApiRepository.authEmployee(login, password)
        _uiState.update {
            it.updateEmployee(res)
        }
    }

    fun logout(){
        _uiState.update { it.updateEmployee(null) }
    }

    suspend fun loadDevices(){
        val res = deviceApiRepository.fetchDevices()
        _uiState.update { it.copy(devicesUiState = DevicesUiState(res)) }
    }

    fun selectDevice(device: Device?){
        _uiState.update { it.updateDeviceUiState(it.devicesUiState.selectDevice(device)) }
    }

    suspend fun saveDevice(device: Device){
        if (deviceApiRepository.saveDevice(device) == null)return
        loadDevices()
    }
}