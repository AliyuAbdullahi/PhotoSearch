package com.br.photosearch.domain.models


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("farm")
    val farm: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("isfamily")
    val isfamily: Int,
    @SerializedName("isfriend")
    val isfriend: Int,
    @SerializedName("ispublic")
    val ispublic: Int,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("title")
    val title: String
)  {
    private val photoUrl = "https://farm$farm.staticflickr.com/$server/${id}_$secret"
    fun large() = "$photoUrl.jpg"
    fun thumbnail() = photoUrl + "_s.jpg"
}