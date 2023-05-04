package com.vyatsu.uhtamobile.viewmodels

import com.vyatsu.uhtamobile.models.Device

data class DevicesUiState(
    val devices: List<Device>,
    val selectedDevice: Device? = null,
) {
    constructor() : this(listOf())
    fun selectDevice(device: Device?) : DevicesUiState{
        if (devices.all{it != device}) return this
        return this.copy(selectedDevice = device)
    }
}
