package com.example.movies_rx

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies_rx.apiCall.MovieViewModel
import com.example.movies_rx.screens.HomePage
import com.example.movies_rx.screens.MovieDetails
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun Navigation(
    modifier: Modifier = Modifier,
){
    val navController = rememberNavController()


    val viewModel : MovieViewModel = hiltViewModel()


    NavHost(
        navController = navController,
        startDestination = "homepage"
    ) {

        composable("homepage") {
            HomePage(
                modifier = modifier,
                viewModel = viewModel,
                navController = navController
            )
        }

        composable("details/{movieId}",
            arguments = listOf( navArgument("movieId"){ type = NavType.IntType})
        )
        { back :NavBackStackEntry  ->
            val movieId = back.arguments?.getInt("movieId")

            val movie = viewModel.movies.value?.find { it.movieId == movieId }

            if (movie != null) {
                MovieDetails(
                    movie = movie
                )
            }

        }
    }
}