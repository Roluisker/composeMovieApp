package com.example.composemovieapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composemovieapp.models.MovieModel

@Composable
fun MoviesListScreen(
    onMovieClick: (MovieModel) -> Unit,
    viewModel: MoviesListViewModel = hiltViewModel()
) {

    val movieUiState by viewModel.moviesListUiState.collectAsState()

    if (movieUiState.isLoading) {
        CircularProgressIndicator()
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(movieUiState.moviesList) { movie ->
                var isFavorite by rememberSaveable { mutableStateOf(false) }

                MovieCard(movie = movie,
                    isFavorite = isFavorite,
                    onFavoriteClick = {
                        isFavorite = !isFavorite
                    },
                    onMovieClick = { movieModel ->
                        onMovieClick(movieModel)
                    })
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 4.dp,
                vertical = 4.dp
            )
    )

    if (movieUiState.error != null) {
        MovieError(stringResource(movieUiState.error?.message ?: -1)) {
            viewModel.getMovies()
        }
    }

    /*
    LazyColumn {
        items(mockMovieList) { movie ->
            var isFavorite by rememberSaveable { mutableStateOf(false) }

            MovieCard(movie = movie,
                isFavorite = isFavorite,
                onFavoriteClick = {
                    isFavorite = !isFavorite
                })
        }
    }*/

}

@Composable
@Preview
fun MoviesListScreenPreview() {
    MoviesListScreen(onMovieClick = {})
}

