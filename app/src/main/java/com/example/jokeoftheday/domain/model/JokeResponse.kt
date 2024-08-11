package com.example.jokeoftheday.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class JokeResponse(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val joke: String
)
