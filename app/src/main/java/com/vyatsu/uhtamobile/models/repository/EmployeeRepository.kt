package com.vyatsu.uhtamobile.models.repository

import com.vyatsu.uhtamobile.models.Employee
import com.vyatsu.uhtamobile.models.dao.EmployeeDao

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    fun getAllEmployee(): List<Employee> = employeeDao.getAll()
    fun insertEmployee(vararg employee: Employee) = employeeDao.insert()
    fun deleteEmployee(vararg employee: Employee) = employeeDao.delete()
}