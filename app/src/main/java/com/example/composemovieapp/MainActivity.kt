package com.example.composemovieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composemovieapp.presentation.detail.DetailsScreen
import com.example.composemovieapp.presentation.home.MoviesListScreen
import com.example.composemovieapp.ui.theme.ComposeMovieAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMovieAppTheme {
                val navController = rememberNavController()

                Scaffold { paddingValues ->
                    NavHost(
                        modifier = Modifier.padding(paddingValues),
                        navController = navController,
                        startDestination = "movieList"
                    ) {
                        composable(route = "movieList") {
                            MoviesListScreen(onMovieClick = { movieModel ->
                                navController.navigate("details")
                            })
                        }
                        composable(route = "details") {
                            DetailsScreen()
                        }
                    }
                }


            }
        }
    }
}