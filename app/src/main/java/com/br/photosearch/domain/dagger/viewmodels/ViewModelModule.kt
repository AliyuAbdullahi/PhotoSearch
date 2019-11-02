package com.br.photosearch.domain.dagger.viewmodels

import androidx.lifecycle.ViewModel
import com.br.photosearch.presentation.photos.PhotosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PhotosViewModel::class)
    abstract fun provideLoginViewModel(loginViewModel: PhotosViewModel): ViewModel
}