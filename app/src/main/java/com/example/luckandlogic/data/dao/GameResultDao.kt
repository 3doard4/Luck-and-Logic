package com.example.luckandlogic.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.luckandlogic.model.GameResult

@Dao
interface GameResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: GameResult)

    // retorna todos ordenados pela data (mais recentes primeiro)
    @Query("SELECT * FROM game_results ORDER BY date DESC")
    suspend fun getAllResults(): List<GameResult>

    @Query("DELETE FROM game_results")
    suspend fun deleteAllResults()
}