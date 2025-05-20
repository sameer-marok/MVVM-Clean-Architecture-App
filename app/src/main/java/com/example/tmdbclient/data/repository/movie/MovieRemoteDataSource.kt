package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

// this will use TMDBService interface functions
interface MovieRemoteDataSource {
    // since getPopularMovies function for TmdbService returns Response, return type should be same
    suspend fun getMovies(): Response<MovieList>

    suspend fun getMoviesBasedOnName(name:String?): Response<MovieList>
}