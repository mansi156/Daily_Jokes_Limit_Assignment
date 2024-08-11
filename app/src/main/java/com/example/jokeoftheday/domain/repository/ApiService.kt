package com.example.jokeoftheday.domain.repository

import com.example.jokeoftheday.domain.model.JokeResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api?format=json")
    suspend fun getJoke(): JokeResponse
}