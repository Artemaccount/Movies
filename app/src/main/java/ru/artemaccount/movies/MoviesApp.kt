package ru.artemaccount.movies

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.artemaccount.movies.service.MovieService

class MoviesApp : Application() {
    companion object {
        private const val BASE_URL = "https://www.omdbapi.com/"
        lateinit var movieService: MovieService
    }

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieService = retrofit.create(MovieService::class.java)
    }
}