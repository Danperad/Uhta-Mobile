package com.vyatsu.uhtamobile.models.api

import com.vyatsu.uhtamobile.models.Device
import retrofit2.awaitResponse

class DeviceApiRepository {
    suspend fun fetchDevices(): List<Device> {
        return try {
            val call = RestClient.client.getService().fetchDevices(20, 0, "")
            val response = call.awaitResponse()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun saveDevice(device: Device): Device? {
        return try {
            val call = RestClient.client.getService().saveDevise(device)
            val response = call.awaitResponse()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}