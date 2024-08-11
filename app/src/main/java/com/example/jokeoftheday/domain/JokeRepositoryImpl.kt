package com.example.jokeoftheday.domain

import android.content.Context
import com.example.jokeoftheday.data.dao.JokeDao
import com.example.jokeoftheday.domain.model.JokeResponse
import com.example.jokeoftheday.domain.repository.ApiService
import com.example.jokeoftheday.domain.repository.JokeRepository
import com.example.jokeoftheday.utils.NetworkUtils
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val jokeDao: JokeDao,
    private val apiService: ApiService,
    private val context: Context
) : JokeRepository {

    override suspend fun fetchAndSaveJoke(): JokeResponse {
        val joke = if (NetworkUtils.isNetworkAvailable(context)) {
            val jokeResponse = apiService.getJoke()
            JokeResponse(joke = jokeResponse.joke)
        } else {
            jokeDao.getLatestJokes().first().firstOrNull()
                ?: return JokeResponse(joke = "No jokes available")
        }

        // Insert joke into the database
        jokeDao.insertJoke(joke)

        // Remove the oldest joke if there are more than 10
        val jokes = jokeDao.getLatestJokes().first()
        if (jokes.size > 10) {
            jokeDao.removeOldestJoke()
        }

        return joke
    }

    override suspend fun getLatestJokes(): List<JokeResponse> {
        return jokeDao.getLatestJokes().first()
    }

    override suspend fun insertJoke(joke: JokeResponse) {
        jokeDao.insertJoke(joke)
    }
}