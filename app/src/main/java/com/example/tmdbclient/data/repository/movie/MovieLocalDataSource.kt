package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.Movie

// this will use MovieDao interface functions
interface MovieLocalDataSource {
    suspend fun getMoviesFromDB():List<Movie>
    suspend fun saveMoviesToDB(movies : List<Movie>)
    suspend fun clearAll()
}