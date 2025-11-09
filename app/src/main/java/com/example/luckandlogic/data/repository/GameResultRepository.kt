package com.example.luckandlogic.data.repository

import com.example.luckandlogic.data.dao.GameResultDao
import com.example.luckandlogic.model.GameResult

class GameResultRepository(private val dao: GameResultDao) {

    suspend fun insertResult(result: GameResult) {
        dao.insertResult(result)
    }

    suspend fun getAllResults(): List<GameResult> {
        return dao.getAllResults()
    }

    suspend fun deleteAllResults() {
        dao.deleteAllResults()
    }
}