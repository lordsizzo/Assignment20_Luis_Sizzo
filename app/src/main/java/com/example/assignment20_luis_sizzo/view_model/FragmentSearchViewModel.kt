package com.example.assignment20_luis_sizzo.view_model

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment20_luis_sizzo.model.ModelListSong
import com.example.assignment20_luis_sizzo.model.data_class.ResultSongResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentSearchViewModel: ViewModel() {

    private val list_songs: MutableLiveData<ResultSongResponse> by lazy {
        MutableLiveData<ResultSongResponse>()
    }
    fun getListSongResponse(): LiveData<ResultSongResponse> {
        return list_songs
    }
    fun loadListSongResponse(value: String) {
        var items: ResultSongResponse?
        CoroutineScope(Dispatchers.Main).launch {
            ModelListSong().ListSongCatched(value).let {
                items = it
            }
            Handler(Looper.getMainLooper()).post {
                list_songs.value = items
            }
        }
    }
}