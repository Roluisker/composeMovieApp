package com.example.composemovieapp.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.composemovieapp.data.MoviesRepository
import com.example.composemovieapp.models.MovieModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

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

            val movies: List<MovieModel> = moviesRepository.getMovies()

            _moviesListUiState.update { movieUiState ->
                movieUiState.copy(
                    moviesList = movies,
                    isLoading = false
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MoviesListViewModel(MoviesRepository())
            }
        }
    }

}

data class MoviesUiState(
    val isLoading: Boolean = false,
    val showErrorMessage: Boolean = false,
    val moviesList: List<MovieModel> = emptyList()
)

