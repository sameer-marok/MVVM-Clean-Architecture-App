package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.artist.Artist

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Insert all artists to DB
    suspend fun saveArtists(artist: List<Artist>)

    @Query("DELETE FROM popular_artists") // Delete all artist from DB
    suspend fun deleteAllArtists()

    @Query("SELECT * FROM popular_artists") // Get all artists from DB
    suspend fun getAllArtists():List<Artist>
}