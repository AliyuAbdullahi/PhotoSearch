package com.br.photosearch.domain.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable

fun Photo.thumbnail(): String {
    return buildUrl(this) + "_m.jpg"
}

private fun buildUrl(photo: Photo) =
    "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}"

fun Photo.large(): String {
    return buildUrl(this) + ".jpg"
}