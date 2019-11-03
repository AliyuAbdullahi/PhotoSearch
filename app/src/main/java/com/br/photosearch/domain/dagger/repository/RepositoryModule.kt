package com.br.photosearch.domain.dagger.repository

import com.br.photosearch.domain.dagger.remoterequests.RetrofitModule
import com.br.photosearch.domain.repository.Repository
import com.br.photosearch.domain.services.PhotosService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(retrofit: PhotosService): Repository {
        return Repository(retrofit)
    }
}