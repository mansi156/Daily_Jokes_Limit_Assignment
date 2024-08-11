package com.example.jokeoftheday.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jokeoftheday.data.dao.JokeDao
import com.example.jokeoftheday.domain.model.JokeResponse

@Database(entities = [JokeResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}