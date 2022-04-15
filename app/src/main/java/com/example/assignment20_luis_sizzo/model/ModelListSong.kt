package com.example.assignment20_luis_sizzo.model

import com.example.assignment20_luis_sizzo.model.data_class.ResultSongResponse
import com.example.assignment20_luis_sizzo.model.init.InitConnection
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ModelListSong: InitConnection() {

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun ListSongCatched(value: String): ResultSongResponse? {
        val catchinData = GlobalScope.async {
            catchDataRetrofit(value)
        }
        return catchinData.await()
    }
}