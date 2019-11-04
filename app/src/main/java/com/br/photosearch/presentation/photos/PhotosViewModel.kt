package com.br.photosearch.presentation.photos

import com.br.photosearch.domain.models.Photo
import com.br.photosearch.domain.models.PhotosResponse
import com.br.photosearch.domain.repository.Repository
import com.br.photosearch.presentation.base.viewmodel.BaseViewModelManager
import com.br.photosearch.presentation.base.viewmodel.ViewEvent
import com.br.photosearch.presentation.base.viewmodel.ViewState
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class PhotosViewModel @Inject constructor(val repository: Repository) :
    BaseViewModelManager.ViewModelState<PhotosViewModel.PhotoViewModelState, ViewEvent>() {
    override val initialState = PhotoViewModelState()

    fun findPhotos(filter: String, apiKey: String, defaultErrorMessage: String) {

        disposable += repository.getPhotosList(
            filter,
            apiKey,
            perPage = "25",
            method = "flickr.photos.search",
            format = "json",
            noJsonCallback = "1"
        )
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    processResponse(it, defaultErrorMessage)
                },
                {
                    processError(it)
                })
    }

    private fun processError(it: Throwable) {
        updateState {
            showErrorMessage("${it.message}")
        }
    }

    private fun processResponse(it: Response<PhotosResponse>, defaultErrorMessage: String) {
        if (it.code() == 200) {
            val result = it.body()
            result?.let {
                it.photos?.allPhotos?.let {
                    updateState {
                        updatePhotos(it)
                    }
                }
            }
        } else {
            if (it.errorBody() != null) {
                updateState {
                    val error = it.errorBody()?.string() ?: defaultErrorMessage
                    showErrorMessage(error)
                }
            }
        }
    }

    data class PhotoViewModelState(
        val photos: List<Photo> = listOf(),
        val loadingPhotos: Boolean = false,
        val error: String = ""
    ) : ViewState {
        fun updatePhotos(photos: List<Photo>) = copy(photos = photos, error = "")
        fun showErrorMessage(errorMessage: String) = copy(error = errorMessage)
    }
}