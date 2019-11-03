package com.br.photosearch.domain.dagger.activities

import com.br.photosearch.presentation.photos.PhotosActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): PhotosActivity
}