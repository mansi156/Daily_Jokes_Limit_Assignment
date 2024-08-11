package com.example.jokeoftheday.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jokeoftheday.R
import com.example.jokeoftheday.domain.model.JokeResponse
import com.example.jokeoftheday.utils.Constants

@Composable
fun MyScreenWithTopBar(jokes: List<JokeResponse>) {
    val customFontFamily = FontFamily(
        Font(R.font.honeybee)
    )
    Scaffold(
        topBar = { TopApp() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            item {
                if (jokes.isNotEmpty()) {
                    LatestJoke(joke = jokes.first())
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = Constants.LATEST_JOKE,
                        fontSize = 30.sp,
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displayMedium.copy(color = Color.Black),
                        )
                }
            }
            items(jokes) { joke ->
                JokeListItem(joke)
            }
        }
    }
}
