package com.vyatsu.uhtamobile.models

import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("login")val login: String,
    @SerializedName("password")val password: String)
