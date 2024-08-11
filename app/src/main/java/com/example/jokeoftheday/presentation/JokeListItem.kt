package com.example.jokeoftheday.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jokeoftheday.R
import com.example.jokeoftheday.domain.model.JokeResponse

@Composable
fun JokeListItem(joke: JokeResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically, // Ensure image has a square aspect ratio
        ) {
            // Image
            Image(
                painter = painterResource(id = R.drawable.emoji), // Replace with your image resource
                contentDescription = null,
                modifier = Modifier
                    .weight(0.2f) // Take equal width
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,

                )

            Column(
                modifier = Modifier
                    .weight(0.8f) // Take equal width
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = joke.joke,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                    fontSize = 20.sp
                )
            }
        }
    }
}
