package com.example.tmdbclient.data.api

import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    //URL: https://api.themoviedb.org/3/movie/popular
    // only need to use endpoints here

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey:String)
    :Response<MovieList> // need to add api_key as query parameter

    // returns list of movies matching name
    @GET("search/movie")
    suspend fun getMoviesFromName(
        @Query("api_key")
        apiKey: String,
        @Query("query")
        name:String?
    ): Response<MovieList>

    @GET("person/popular")
    suspend fun getPopularArtists(
        @Query("api_key")
        apiKey:String)
            :Response<ArtistList>

    @GET("search/person")
    suspend fun getArtistsFromName(
        @Query("api_key")
        apiKey: String,
        @Query("query")
        name:String?
    ): Response<ArtistList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key")
        apiKey:String)
            :Response<TvShowList>

    @GET("search/tv")
    suspend fun getTvShowsFromName(
        @Query("api_key")
        apiKey: String,
        @Query("query")
        name:String?
    ): Response<TvShowList>
}