package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource
): ArtistRepository {
    override suspend fun getArtists(): List<Artist> {
        return getArtistsFromCache() // get form cache first
    }

    override suspend fun updateArtists(): List<Artist> {
        // To update movies, we first need to get new movies from API
        val newArtistsList = getArtistsFromAPI()
        // Then clear DB table
        artistLocalDataSource.clearAll()
        // Save new movies to DB and cache
        artistLocalDataSource.saveArtistsToDB(newArtistsList)
        artistCacheDataSource.saveArtistsToCache(newArtistsList)

        return newArtistsList
    }

    override suspend fun getArtistsBasedOnName(name: String?): List<Artist> {
        lateinit var artistList: List<Artist>

        try{
            val response = artistRemoteDataSource.getArtistBasedOnName(name)
            val body = response.body()

            if (body!=null) artistList = body.artists
        } catch (exception:Exception) {
            Log.i("MyTag", exception.message.toString())
        }

        return artistList
    }

    private suspend fun getArtistsFromAPI():List<Artist>{
        lateinit var artistList: List<Artist>

        try{
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()

            if (body!=null) artistList = body.artists
        } catch (exception:Exception) {
            Log.i("MyTag", exception.message.toString())
        }

        return artistList
    }

    private suspend fun getArtistsFromDB():List<Artist>{
        lateinit var artistList: List<Artist>

        try{
            artistList = artistLocalDataSource.getArtistsFromDB()
        } catch (exception:Exception) {
            Log.i("MyTag", exception.message.toString())
        }

        if (artistList.isNotEmpty()) return artistList

        else{
            artistList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDB(artistList)
        }
        return artistList
    }

    private suspend fun getArtistsFromCache():List<Artist>{
        lateinit var artistList: List<Artist>

        try{
            artistList = artistCacheDataSource.getArtistsFromCache()
        } catch (exception:Exception) {
            Log.i("MyTag", exception.message.toString())
        }

        if (artistList.isNotEmpty()) return artistList

        else{
            artistList = getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }
}