package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.Movie

class MovieCacheDataSourceImpl: MovieCacheDataSource {

    // We create an arrayList of movie instances
    /* When user launches app and load movie data for first time,
    We will assign that list to this arrayList, for subsequent times,
    we can use cached list instead of using database */
    private var movieList = ArrayList<Movie>()
    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        // clear current movie list
        movieList.clear()
        // save new list to movies arrayList
        movieList = ArrayList(movies)
    }
}