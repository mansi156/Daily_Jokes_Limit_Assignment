package com.example.jokeoftheday.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeoftheday.domain.model.JokeResponse
import com.example.jokeoftheday.domain.usecases.FetchJokeUseCase
import com.example.jokeoftheday.domain.usecases.GetLatestJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getLatestJokesUseCase: GetLatestJokesUseCase,
    private val fetchJokeUseCase: FetchJokeUseCase
) : ViewModel() {

    private val _jokes = MutableStateFlow<List<JokeResponse>>(emptyList())
    val jokes: StateFlow<List<JokeResponse>> = _jokes

    init {
        fetchInitialJoke()
        fetchJokesPeriodically()
    }

    private fun fetchInitialJoke() {
        viewModelScope.launch {
            fetchAndSaveJoke()
        }
    }

    private fun fetchJokesPeriodically() {
        viewModelScope.launch {
            while (true) {
                delay(6000) // 1 minute delay
                fetchAndSaveJoke()
            }
        }
    }

    private fun fetchAndSaveJoke() {
        viewModelScope.launch {
            val joke = fetchJokeUseCase()
            val currentJokes = getLatestJokesUseCase()
            _jokes.value = listOf(joke) + currentJokes.take(9) // Add new joke at the top, keep only the last 10
        }
    }

}