package com.example.jokeoftheday.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokeoftheday.ui.theme.JokeOfTheDayTheme
import androidx.compose.runtime.collectAsState

@Composable
fun JokeScreen() {
    val jokeViewModel: JokeViewModel = hiltViewModel()
    val jokes by jokeViewModel.jokes.collectAsState()
    JokeOfTheDayTheme {
        MyScreenWithTopBar(jokes)
    }
}