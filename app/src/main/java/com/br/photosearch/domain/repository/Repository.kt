package com.br.photosearch.domain.repository

import com.br.photosearch.domain.services.PhotosService
import javax.inject.Inject

class Repository @Inject constructor(private val photoService: PhotosService) {

    fun getPhotos(searchTag: String) = photoService.getPhotos(searchTag)
}