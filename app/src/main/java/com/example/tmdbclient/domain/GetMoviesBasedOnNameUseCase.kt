package com.example.tmdbclient.domain

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository

class GetMoviesBasedOnNameUseCase(private val moviesRepository: MovieRepository) {

    // this variable holds the name to be searched
    // we will update its value in activity via ViewModel function
    var name: String? = null
        set(value){
            value?.let {
                field = value
            } ?: throw java.lang.IllegalArgumentException("Name cannot be empty")
        }

    suspend fun execute(): List<Movie>?  = moviesRepository.getMoviesBasedOnName(name)
}