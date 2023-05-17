package com.vyatsu.uhtamobile.models.api

import com.vyatsu.uhtamobile.models.AuthModel
import com.vyatsu.uhtamobile.models.Device
import com.vyatsu.uhtamobile.models.Employee
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface DanperadApi {
    @Headers("Accept: Application/json")
    @GET("devices/?")
    fun fetchDevices(
        @Query("count") count: Int,
        @Query("start") start: Int,
        @Query("search") search: String
    ): Call<List<Device>>

    @Headers("Accept: Application/json")
    @POST("auth/")
    fun authUser(
        @Body model: AuthModel
    ): Call<Employee>

    @Headers("Accept: Application/json")
    @POST("devices/")
    fun saveDevise(
        @Body device: Device
    ): Call<Device>
}