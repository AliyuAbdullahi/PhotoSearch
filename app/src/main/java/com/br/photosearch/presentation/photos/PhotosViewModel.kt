package com.br.photosearch.presentation.photos

import com.br.photosearch.domain.models.Photo
import com.br.photosearch.domain.repository.Repository
import com.br.photosearch.presentation.base.viewmodel.BaseViewModelManager
import com.br.photosearch.presentation.base.viewmodel.ViewEvent
import com.br.photosearch.presentation.base.viewmodel.ViewState
import javax.inject.Inject

class PhotosViewModel @Inject constructor(val repository: Repository) :
    BaseViewModelManager.ViewModelState<PhotosViewModel.PhotoViewModelState, ViewEvent>() {
    override val initialState = PhotoViewModelState()

    data class PhotoViewModelState(
        val photos: List<Photo> = listOf(),
        val loadingPhotos: Boolean = false,
        val error: String = ""
    ) : ViewState {
        fun updatePhotos(photos: List<Photo>) = copy(photos = photos, error = "")
        fun showErrorMessage(errorMessage: String) = copy(error = errorMessage)
    }
}