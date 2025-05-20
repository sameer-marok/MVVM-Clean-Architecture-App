package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.data.api.TMDBService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// this module is to provide dependencies related to Retrofit api
@Module
class NetModule(private val baseUrl:String) {

    // provides Retrofit Instance
    @Singleton
    @Provides
    fun provideRetroFit(): Retrofit{
        // create retrofit instance using its builder function call and return it
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    // provides TMDBService instance
    @Singleton
    @Provides
    fun providesTMDBService(retrofit: Retrofit):TMDBService{
        return retrofit.create(TMDBService::class.java)
    }

    private val interceptor = HttpLoggingInterceptor().apply {
        //BODY -> Logs request and response lines and their respective headers and bodies (if present).
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    // create http client instance
    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
            // ConnectTimeout is time in which our app should establish with server (default is 10)
            .connectTimeout(30, TimeUnit.SECONDS)
            // readTimeout is max time gap b/w arrival of 2 data packets when waiting for server's response
            .readTimeout(20, TimeUnit.SECONDS)
            // writeTimeout is max time gap b/w 2 data packets when sending to server
            .writeTimeout(25, TimeUnit.SECONDS)
    }.build()

}