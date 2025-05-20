package com.example.tmdbclient.data.repository.movie

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
): MovieRepository {

    override suspend fun getMovies(): List<Movie> {
        // We will get movie from Cache first
        /* If cache is available, system will return it,
         otherwise system will look into DB,
         and finally system will fetch form API if no data in either Cache or DB */
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie> {
        // To update movies, we first need to get new movies from API
        val newMovieList = getMoviesFromAPI()
        // Then clear DB table
        movieLocalDataSource.clearAll()
        // Save new movies to DB and cache
        movieLocalDataSource.saveMoviesToDB(newMovieList)
        movieCacheDataSource.saveMoviesToCache(newMovieList)

        return newMovieList
    }

    override suspend fun getMoviesBasedOnName(name: String?): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            // Get Response<MovieList>
            val response = movieRemoteDataSource.getMoviesBasedOnName(name)

            // get response body
            val body:MovieList? = response.body()

            if (body != null){
                // assign movies we get form api to movieList
                movieList = body.movies
            }
        } catch (exception: Exception){
            Log.i("MyTag", exception.message.toString())
        }
        return movieList
    }


    private suspend fun getMoviesFromAPI():List<Movie>{
        lateinit var movieList: List<Movie>

        try {
            // Get Response<MovieList>
            val response = movieRemoteDataSource.getMovies()

            // get response body
            val body:MovieList? = response.body()

            if (body != null){
                // assign movies we get form api to movieList
                movieList = body.movies
            }
        } catch (exception: Exception){
            Log.i("MyTag", exception.message.toString())
        }
        return movieList
    }

    private suspend fun getMoviesFromDB():List<Movie>{
        lateinit var movieList: List<Movie>

        try {
            movieList = movieLocalDataSource.getMoviesFromDB()

        } catch (exception: Exception){
            Log.i("MyTag", exception.message.toString())
        }

        // If size of list is > 0, means movie data is available in DB
        if (movieList.isNotEmpty()){
            return movieList
        }
        // Else, we need to fetch data form API, save it to DB
        else{
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }

    private suspend fun getMoviesFromCache():List<Movie>{
        lateinit var movieList: List<Movie>

        try {
            movieList = movieCacheDataSource.getMoviesFromCache()

        } catch (exception: Exception){
            Log.i("MyTag", exception.message.toString())
        }

        // If cached data not available, we need to get data form DB and save it to cache
        if (movieList.isNotEmpty()){
            return movieList
        }
        else{
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}