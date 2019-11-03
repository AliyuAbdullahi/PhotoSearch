package com.br.photosearch.domain.models


import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("perpage")
    val perPage: Int,
    @SerializedName("photo")
    val allPhotos: List<Photo>,
    @SerializedName("total")
    val total: String
)