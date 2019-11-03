package com.br.photosearch.domain.repository

import com.br.photosearch.domain.services.PhotosService
import javax.inject.Inject

class Repository @Inject constructor(private val photoService: PhotosService) {

    fun getPhotosList(
        searchTag: String,
        apiKey: String,
        perPage: String,
        method: String,
        format: String,
        noJsonCallback: String
    ) = photoService.getPhotos(
        searchTag,
        apiKey,
        perPage,
        method,
        format,
        noJsonCallback
    )
}