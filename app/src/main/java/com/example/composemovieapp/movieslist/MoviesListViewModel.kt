package com.example.composemovieapp.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemovieapp.models.MovieModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {

    private val _moviesListUiState = MutableStateFlow(MoviesUiState())
    val moviesListUiState: StateFlow<MoviesUiState> = _moviesListUiState.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _moviesListUiState.update {
                it.copy(isLoading = true)
            }

            delay(2000L)

            val movies: List<MovieModel> = mockMovieList

            _moviesListUiState.update { movieUiState ->
                movieUiState.copy(
                    moviesList = movies,
                    isLoading = false
                )
            }
        }
    }
}

data class MoviesUiState(
    val isLoading: Boolean = false,
    val showErrorMessage: Boolean = false,
    val moviesList: List<MovieModel> = emptyList()
)

val mockMovieList = listOf(
    MovieModel(
        0,
        "Movie 1",
        "https://images.unsplash.com/photo-1561037404-61cd46aa615b?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        false
    ),
    MovieModel(
        1,
        "Movie 2",
        "https://images.unsplash.com/photo-1561037404-61cd46aa615b?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        false
    ),
    MovieModel(
        2,
        "Movie 3",
        "https://images.unsplash.com/photo-1561037404-61cd46aa615b?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        false
    ),
    MovieModel(
        3,
        "Movie 4",
        "https://images.unsplash.com/photo-1561037404-61cd46aa615b?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        false
    )
)