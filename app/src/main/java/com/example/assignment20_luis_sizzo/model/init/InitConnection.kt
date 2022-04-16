package com.example.assignment20_luis_sizzo.model.init

import android.util.Log
import com.example.assignment20_luis_sizzo.model.connection.SongsAPI
import com.example.assignment20_luis_sizzo.model.data_class.ResultSongResponse

abstract class InitConnection {

    fun catchDataRetrofit(value: String, limit: String): ResultSongResponse? {
        var items: ResultSongResponse? = null
        SongsAPI.initRetrofit()
            .getSongs(value, "music", "song", limit)
            .execute()
            .body()?.let {
            items = it
        } ?: run {
            Log.d("catchDataRetrofit", "Had an error")
        }

        return items
    }
}