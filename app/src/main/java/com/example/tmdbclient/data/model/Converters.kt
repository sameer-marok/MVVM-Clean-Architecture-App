package com.example.tmdbclient.data.model

import androidx.room.TypeConverter
import com.example.tmdbclient.data.model.movie.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromMovieList(movies: List<Movie>?): String? {
        return gson.toJson(movies)
    }

    @TypeConverter
    fun toMovieList(data: String?): List<Movie>? {
        if (data.isNullOrEmpty()) return null
        val listType = object : TypeToken<List<Movie>>() {}.type
        return gson.fromJson(data, listType)
    }

}