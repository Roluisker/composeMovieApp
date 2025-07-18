package com.example.composemovieapp.presentation.detail.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviescourseapp.models.details.MovieDetailsModel

@Composable
fun MovieDetailsContent(
    movieDetailsModel: MovieDetailsModel,
    onBack: () -> Unit,
    onUpdateFavorite: (MovieDetailsModel) -> Unit
) {
    Scaffold(topBar = {
        MovieDetailsTopBar(
            movieDetailsModel = movieDetailsModel,
            onBack = { onBack() },
            onUpdateFavorite = { movie -> onUpdateFavorite(movie) })
    }) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())) {
            MovieDetailsHeader(
                movieDetailsModel = movieDetailsModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            MovieDetailsBody(movieDetailsModel = movieDetailsModel)
        }
    }
}