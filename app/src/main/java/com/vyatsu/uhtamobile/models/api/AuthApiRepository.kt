package com.vyatsu.uhtamobile.models.api

import com.vyatsu.uhtamobile.models.AuthModel
import com.vyatsu.uhtamobile.models.Employee
import retrofit2.awaitResponse

class AuthApiRepository {
    suspend fun authEmployee(login: String, password: String): Employee? {
        return try {
            val model = AuthModel(login, password)
            val call = RestClient.client.getService().authUser(model)
            val response = call.awaitResponse()
            if(response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception){
            null
        }
    }
}