package com.example.assignment20_luis_sizzo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.assignment20_luis_sizzo.databinding.FragmentViewSearchBinding
import com.example.assignment20_luis_sizzo.model.data_class.ResultSongResponse
import com.example.assignment20_luis_sizzo.ui.SongsAdapter
import com.example.assignment20_luis_sizzo.utils.Dialogs
import com.example.assignment20_luis_sizzo.utils.layoutManagerCustom
import com.example.assignment20_luis_sizzo.view_model.FragmentSearchViewModel

class FragmentSearch(private val value: String): Fragment() {

    lateinit var binding: FragmentViewSearchBinding
    private lateinit var viewModel: FragmentSearchViewModel
    var dialog: androidx.appcompat.app.AlertDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentViewSearchBinding.inflate(inflater, container, false)


        initViews()
        return binding.root
    }

    private fun initViews() {

        dialog = Dialogs().showDialog("Loading...", requireContext())
        viewModel = ViewModelProvider(this)[FragmentSearchViewModel::class.java]
        binding.recyclerView.layoutManagerCustom()
        binding.swipeRefresh.setOnRefreshListener {
            dialog = Dialogs().showDialog("Loading...", requireContext())
            viewModel.loadListSongResponse(value)
        }

        initViewModelObservable()
    }
    fun initViewModelObservable(){
        val observerResult = Observer<ResultSongResponse> {
            val adapter =  SongsAdapter(it.results, requireContext())
            adapter.apply {
                binding.recyclerView.adapter = this
                binding.swipeRefresh.isRefreshing = false
            }
            dialog?.dismiss()
        }
        viewModel.getListSongResponse().observe(viewLifecycleOwner, observerResult)
        viewModel.loadListSongResponse(value)
    }
}