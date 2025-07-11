package com.example.composemovieapp.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.composemovieapp.data.MoviesRepository
import com.example.composemovieapp.data.RetrofitClient
import com.example.composemovieapp.models.MovieModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException

class MoviesListViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _moviesListUiState = MutableStateFlow(MoviesUiState())
    val moviesListUiState: StateFlow<MoviesUiState> = _moviesListUiState.asStateFlow()

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {

            try {

                _moviesListUiState.update {
                    it.copy(
                        isLoading = true,
                        error = null,
                    )
                }

                delay(2000L)

                val movies: List<MovieModel> = moviesRepository.getMovies()

                _moviesListUiState.update { movieUiState ->
                    movieUiState.copy(
                        moviesList = movies,
                        isLoading = false,
                    )
                }

            } catch (exception: Exception) {
                val errorEnum = when {
                    exception is UnknownHostException -> ErrorMessage.INTERNET_CONNECTION
                    else -> ErrorMessage.DEFAULT
                }

                _moviesListUiState.update {
                    it.copy(
                        isLoading = false,
                        error = errorEnum
                    )
                }
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MoviesListViewModel(MoviesRepository(RetrofitClient.service))
            }
        }
    }

}

data class MoviesUiState(
    val isLoading: Boolean = false,
    val error: ErrorMessage? = null,
    val moviesList: List<MovieModel> = emptyList()
)

