package ru.artemaccount.movies.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.artemaccount.movies.model.SearchResponse


interface MovieService {
    // https://www.omdbapi.com/?s=the%20fast%20and%20the%20furious&apikey=418f400d

    // the%20fast%20and%20the%20furious

    @GET(".")
    fun listMovies(
        @Query("s") film: String,
        @Query("apikey") apikey: String = "418f400d"
    ): Call<SearchResponse>
}