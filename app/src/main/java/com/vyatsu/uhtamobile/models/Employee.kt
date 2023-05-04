package com.vyatsu.uhtamobile.models

data class Employee(
    val id: Int,
    val login: String,
    val lastName: String,
    val firstName: String,
    val middleName: String?
)
