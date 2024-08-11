package com.example.jokeoftheday.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jokeoftheday.domain.model.JokeResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: JokeResponse)

    @Query("SELECT * FROM jokes ORDER BY id DESC")
    fun getLatestJokes(): Flow<List<JokeResponse>>

    @Query("DELETE FROM jokes WHERE id IN (SELECT id FROM jokes ORDER BY id ASC LIMIT 1)")
    suspend fun removeOldestJoke()
}