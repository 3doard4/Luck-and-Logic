package com.example.luckandlogic.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.luckandlogic.data.dao.GameResultDao
import com.example.luckandlogic.model.GameResult
import com.example.luckandlogic.model.User

@Database(entities = [User::class, GameResult::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun gameResultDao(): GameResultDao
}