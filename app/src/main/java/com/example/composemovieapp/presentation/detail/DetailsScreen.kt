package com.example.composemovieapp.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composemovieapp.presentation.detail.componets.MovieDetailsContent

@Composable
fun DetailsScreen(
    movieId: String?,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {

    val movieDetailsUiState by viewModel.movieDetailsUiState.collectAsState()

    // ejecutar una corrutina solo cuando un valor cambia
    // cuando el movie id cambie se ejecuta esto, en este caso solo cuando inicia
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId.orEmpty())
    }

    Box(modifier = Modifier.fillMaxSize()) {

        LaunchedEffect(movieDetailsUiState.error) {
            if (movieDetailsUiState.error != null) {

            }
        }

        if (movieDetailsUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {

            movieDetailsUiState.movieDetailsModel?.let {
                MovieDetailsContent(it)
            }

        }

    }
}