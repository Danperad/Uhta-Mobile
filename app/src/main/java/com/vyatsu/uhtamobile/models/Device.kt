package com.vyatsu.uhtamobile.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Device(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "csss") val csss: Int,
    @ColumnInfo(name = "nr3") val nr3: Int,
    @ColumnInfo(name = "unitType") val unitType: String,
    @ColumnInfo(name = "inOperation") val inOperation: Int,
    @ColumnInfo(name = "inStock") val inStock: Int,
)
