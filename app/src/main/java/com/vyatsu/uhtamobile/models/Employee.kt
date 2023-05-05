package com.vyatsu.uhtamobile.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "middleName") val middleName: String?
)
