package com.example.jokeoftheday.domain.repository

import com.example.jokeoftheday.domain.model.JokeResponse

interface JokeRepository {
    suspend fun fetchAndSaveJoke() : JokeResponse
    suspend fun getLatestJokes(): List<JokeResponse>
    suspend fun insertJoke(joke: JokeResponse)
}