package com.vyatsu.uhtamobile.models.repository

import com.vyatsu.uhtamobile.models.Device
import com.vyatsu.uhtamobile.models.dao.DeviceDao

class DeviceRepository(private val deviceDao: DeviceDao) {
    fun getAllDevices(): List<Device> = deviceDao.getAll()
    fun insertDevices(vararg device: Device) = deviceDao.insert()
    fun updateDevices(vararg device: Device) = deviceDao.update()
}