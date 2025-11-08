package com.example.luckandlogic.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.luckandlogic.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}