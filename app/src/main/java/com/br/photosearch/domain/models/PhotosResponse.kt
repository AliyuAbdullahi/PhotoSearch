package com.br.photosearch.domain.models


import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    @SerializedName("photos")
    val photos: Photos,
    @SerializedName("stat")
    val stat: String
)