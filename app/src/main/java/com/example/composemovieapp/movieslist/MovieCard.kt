package com.example.composemovieapp.movieslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.composemovieapp.data.mockMovieList
import com.example.composemovieapp.models.MovieModel

@Composable
fun MovieCard(
    movie: MovieModel, isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Card(elevation = CardDefaults.cardElevation(2.dp)) {

        //var isFavorite by rememberSaveable { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = movie.imageUrl,
                contentDescription = "Movie Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        onFavoriteClick()
                        //isFavorite = !isFavorite
                    }
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true
    //showSystemUi = true
)
fun MoviesCardPreview() {
    MovieCard(mockMovieList.first(), true, onFavoriteClick = {})
}