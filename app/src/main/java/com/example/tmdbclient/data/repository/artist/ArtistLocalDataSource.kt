package com.example.tmdbclient.data.repository.artist

import com.example.tmdbclient.data.model.artist.Artist

interface ArtistLocalDataSource {
    suspend fun getArtistsFromDB():List<Artist>
    suspend fun saveArtistsToDB(artist : List<Artist>)
    suspend fun clearAll()
}