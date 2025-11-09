package com.example.luckandlogic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_results")
data class GameResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // nomes usados no seu código das telas
    val gameName: String = "",
    val result: String = "",
    val points: Int = 0,

    // timestamp em millis
    val date: Long = System.currentTimeMillis()
)