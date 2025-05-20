package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.movie.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Insert all movies to DB
    suspend fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM popular_movies") // Delete all movies from DB
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM popular_movies") // Get all movies from DB
    suspend fun getAllMovies():List<Movie>
}