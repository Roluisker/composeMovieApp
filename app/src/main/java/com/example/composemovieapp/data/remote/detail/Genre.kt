package com.example.composemovieapp.data.remote.detail


import com.squareup.moshi.Json

data class Genre(
    @Json(name = "id")
    var id: Int,
    @Json(name = "name")
    var name: String
)