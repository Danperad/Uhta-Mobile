package com.vyatsu.uhtamobile.models.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {
    private val service: DanperadApi
    fun getService() = service
    companion object {
        val client = RestClient()
    }
    init {
        val baseUrl = "http://danperad.ru:8081/api/"
        val client = OkHttpClient.Builder()
        client.connectTimeout(2, TimeUnit.MINUTES)
        client.readTimeout(2, TimeUnit.MINUTES)
        client.writeTimeout(2, TimeUnit.MINUTES)
        val gson = GsonBuilder().serializeNulls().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson))
            .client(client.build()).build()
        service = retrofit.create(DanperadApi::class.java)
    }
}