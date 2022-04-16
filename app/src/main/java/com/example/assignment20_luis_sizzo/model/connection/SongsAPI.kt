package com.example.assignment20_luis_sizzo.model.connection

import com.example.assignment20_luis_sizzo.model.data_class.ResultSongResponse
import com.example.assignment20_luis_sizzo.utils.*
import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SongsAPI {
    @GET(END_POINT)
    fun getSongs(
        @Query(PARAM_TERM) term: String,
        @Query(PARAM_MEDIA) media: String,
        @Query(PARAM_ENTITY) entity: String,
        @Query(PARAM_LIMIT)  limit: String,
    ): Call <ResultSongResponse>


    companion object{
        fun initRetrofit(): SongsAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SongsAPI::class.java)
        }
    }
}