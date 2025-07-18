package com.example.composemovieapp.presentation.detail.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviescourseapp.models.details.MovieDetailsModel

@Composable
fun MovieDetailsBody(
    movieDetailsModel: MovieDetailsModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MovieInfoContainer(movieDetailsModel = movieDetailsModel)
        MovieDescriptionContainer(movieDetailsModel.overview)
    }
}