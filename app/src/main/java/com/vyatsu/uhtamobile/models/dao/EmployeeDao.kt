package com.vyatsu.uhtamobile.models.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vyatsu.uhtamobile.models.Employee

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee")
    fun getAll(): List<Employee>

    @Insert
    fun insert(vararg employee: Employee)
    @Delete
    fun delete(vararg employee: Employee)
}