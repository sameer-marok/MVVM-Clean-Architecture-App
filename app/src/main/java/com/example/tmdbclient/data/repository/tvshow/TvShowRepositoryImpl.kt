package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource
):TvShowRepository {
    override suspend fun getTvShows(): List<TvShow> {
        return getTvShowsFromCache() // get from cache first
    }

    override suspend fun updateTvShows(): List<TvShow> {
        // To update tvShows, we first need to get new tvShows from API
        val newTvShowList = getTvShowsFromAPI()
        // Then clear DB table
        tvShowLocalDataSource.clearAll()
        // Save new movies to DB and cache
        tvShowLocalDataSource.saveTvShowsToDB(newTvShowList)
        tvShowCacheDataSource.saveTvShowsToCache(newTvShowList)

        return newTvShowList
    }

    override suspend fun getTvShowsBasedOnName(name: String?): List<TvShow> {
        lateinit var tvShowList: List<TvShow>

        try {
            val response = tvShowRemoteDataSource.getTvShowsBasedOnName(name)
            val body = response.body()

            if (body != null) tvShowList = body.tvShows
        } catch (exception:Exception){
            Log.i("MyTag", exception.message.toString())
        }

        return tvShowList
    }

    private suspend fun getTvShowsFromAPI():List<TvShow>{
        lateinit var tvShowList: List<TvShow>

        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()

            if (body != null) tvShowList = body.tvShows
        } catch (exception:Exception){
            Log.i("MyTag", exception.message.toString())
        }

        return tvShowList
    }

    private suspend fun getTvShowsFromDB():List<TvShow>{
        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()

        } catch (exception:Exception){
            Log.i("MyTag", exception.message.toString())
        }

        if (tvShowList.isNotEmpty()) return tvShowList
        else{
            tvShowList = getTvShowsFromAPI() //if DB is empty, get from API
            tvShowLocalDataSource.saveTvShowsToDB(tvShowList) // save data to DB
        }

        return tvShowList
    }

    private suspend fun getTvShowsFromCache():List<TvShow>{
        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()

        } catch (exception:Exception){
            Log.i("MyTag", exception.message.toString())
        }

        if (tvShowList.isNotEmpty()) return tvShowList
        else{
            tvShowList = getTvShowsFromDB()//if cache is empty, get from DB
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList) // save data to cache
        }

        return tvShowList
    }

}