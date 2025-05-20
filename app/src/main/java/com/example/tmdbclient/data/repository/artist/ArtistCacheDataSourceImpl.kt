package com.example.tmdbclient.data.repository.artist

import com.example.tmdbclient.data.model.artist.Artist

class ArtistCacheDataSourceImpl:ArtistCacheDataSource {

    private var artistList = ArrayList<Artist>()
    override suspend fun getArtistsFromCache(): List<Artist> {
        return artistList
    }

    override suspend fun saveArtistsToCache(artist: List<Artist>) {
        // clear current artist list
        artistList.clear()
        // save new list to artist arrayList
        artistList = ArrayList(artist)
    }
}