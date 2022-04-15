package com.example.assignment20_luis_sizzo.model.data_class


data class ResultSongResponse(
    val resultCount: Int,
    val results: List<SongResponse>
)

data class SongResponse(
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Float,
    val previewUrl: String,
)