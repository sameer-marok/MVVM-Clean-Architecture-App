package com.example.tmdbclient.domain

import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.domain.repository.TvShowRepository

class GetTvShowsBasedOnNameUseCase(private val tvShowRepository: TvShowRepository) {

    // this variable holds the name to be searched
    // we will update its value in activity via ViewModel function
    var name: String? = null
        set(value){
            value?.let {
                field = value
            } ?: throw java.lang.IllegalArgumentException("Name cannot be empty")
        }

    suspend fun execute(): List<TvShow>? = tvShowRepository.getTvShowsBasedOnName(name)
}