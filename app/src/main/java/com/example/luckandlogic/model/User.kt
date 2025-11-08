package com.example.luckandlogic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uidFirebase: String = "",
    val name: String = "",
    val email: String = ""
)