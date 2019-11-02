package com.br.photosearch.presentation.base.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.photosearch.domain.dagger.viewmodels.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @JvmOverloads
    fun <T : ViewModel> getViewModel(clazz: Class<T>, key: String? = null): T {
        return if (key != null) {
            ViewModelProvider(this, viewModelFactory).get(key, clazz)
        } else {
            ViewModelProvider(this, viewModelFactory).get(clazz)
        }
    }

    inline fun <reified T : ViewModel> getViewModel(key: String? = null): T {
        return getViewModel(T::class.java, key)
    }
}