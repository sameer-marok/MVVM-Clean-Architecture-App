package com.example.tmdbclient.data.model.artist


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.tmdbclient.data.model.Converters
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.movie.MovieList
import com.google.gson.annotations.SerializedName
@Entity(tableName = "popular_artists")
data class Artist(

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("profile_path")
    val profilePath: String?,

    @SerializedName("known_for")
    @TypeConverters(Converters::class)
    val knownFor: List<Movie>?
)