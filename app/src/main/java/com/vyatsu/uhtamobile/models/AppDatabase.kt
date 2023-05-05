package com.vyatsu.uhtamobile.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vyatsu.uhtamobile.models.dao.DeviceDao
import com.vyatsu.uhtamobile.models.dao.EmployeeDao

@Database(entities = [Device::class, Employee::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun deviceDao() : DeviceDao
    abstract fun employeeDao() : EmployeeDao

    companion object {
        @Volatile
        private var INSTANSE: AppDatabase? = null
        fun getDataBase(context: Context): AppDatabase {
            val tempInstance = INSTANSE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "uhta").build()
                INSTANSE = instance
                return instance
            }
        }
    }
}