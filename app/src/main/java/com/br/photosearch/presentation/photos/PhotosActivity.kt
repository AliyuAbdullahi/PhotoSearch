package com.br.photosearch.presentation.photos

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.br.photosearch.R
import com.br.photosearch.presentation.base.view.BaseActivity
import dagger.android.DaggerActivity

class PhotosActivity : BaseActivity() {
    private lateinit var viewModel: PhotosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        viewModel = getViewModel()

        viewModel.state.observe(this, Observer {

        })
    }
}
