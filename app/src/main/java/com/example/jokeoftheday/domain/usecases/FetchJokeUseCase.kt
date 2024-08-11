package com.example.jokeoftheday.domain.usecases

import com.example.jokeoftheday.domain.model.JokeResponse
import com.example.jokeoftheday.domain.repository.JokeRepository
import javax.inject.Inject

class FetchJokeUseCase @Inject constructor(private val repository: JokeRepository) {
    suspend operator fun invoke(): JokeResponse {
        return repository.fetchAndSaveJoke()
    }
}