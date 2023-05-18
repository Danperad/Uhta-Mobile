package com.vyatsu.uhtamobile.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vyatsu.uhtamobile.models.Device

@Dao
interface DeviceDao {
    @Query("SELECT * FROM device")
    fun getAll(): List<Device>

    @Insert
    fun insert(vararg device: Device)

    @Update
    fun update(vararg device: Device)

    @Query("DELETE FROM device")
    fun clear()
}