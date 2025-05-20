package com.example.tmdbclient.domain

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository

class UpdateMoviesUseCase(private val moviesRepository: MovieRepository) {
    suspend fun execute():List<Movie>? = moviesRepository.updateMovies()
}