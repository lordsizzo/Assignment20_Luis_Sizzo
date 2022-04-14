package com.example.assignment20_luis_sizzo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.assignment20_luis_sizzo.databinding.FragmentViewSearchBinding
import com.example.assignment20_luis_sizzo.model.ResultSongResponse
import com.example.assignment20_luis_sizzo.model.connection.SongAPI
import com.example.assignment20_luis_sizzo.ui.SongsAdapter
import com.example.assignment20_luis_sizzo.utils.layoutManagerCustom
import com.example.assignment20_luis_sizzo.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSearch(val value: String): Fragment() {

    lateinit var binding: FragmentViewSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentViewSearchBinding.inflate(inflater, container, false)

        binding.swipeRefresh.setOnRefreshListener {
            initRetrofit(value)
        }
        initRetrofit(value)
        return binding.root
    }

    private fun initRetrofit(value: String) {
        SongAPI.initRetrofit().getSongs(value, "music", "song", "50").enqueue(
            object : Callback<ResultSongResponse> {
                override fun onResponse(
                    call: Call<ResultSongResponse>,
                    response: Response<ResultSongResponse>
                ) {
                    Log.d("FragmentSearch", "onResponse: ${response.body()}")

                    if(response.isSuccessful) {
                        response.body()?.let {
                            updateUI(it)
                        } ?: errorMessage(response.message())
                    }
                }

                override fun onFailure(call: Call<ResultSongResponse>, t: Throwable) {
                    Log.d("FragmentSearch", "onFailure: ${t.message}")
                }
            }
        )
    }

    private fun updateUI(it: ResultSongResponse) {

        requireActivity().toast("Found ${it.resultCount} results")
        binding.recyclerView.layoutManagerCustom()
        binding.recyclerView.adapter = SongsAdapter(it.results, requireContext())
        binding.swipeRefresh.isRefreshing = false
        Log.d("FragmentSearch", "updateUI: ${it}")
    }

    private fun errorMessage(message: String) {

    }
}