package com.example.tmdbclient.data.repository.tvshow

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey:String
) :TvShowRemoteDataSource{
    override suspend fun getTvShows(): Response<TvShowList> {
        return tmdbService.getPopularTvShows(apiKey)
    }

    override suspend fun getTvShowsBasedOnName(name: String?): Response<TvShowList> {
        return tmdbService.getTvShowsFromName(apiKey, name)
    }
}