package com.example.tmdbclient.data.repository.artist

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey:String
) : ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<ArtistList> {
       return tmdbService.getPopularArtists(apiKey)
    }

    override suspend fun getArtistBasedOnName(name: String?): Response<ArtistList> {
        return tmdbService.getArtistsFromName(apiKey, name)
    }
}