package com.br.photosearch.presentation.photos

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.br.photosearch.R
import com.br.photosearch.presentation.base.view.BaseActivity

class PhotosActivity : BaseActivity() {
    private lateinit var viewModel: PhotosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        viewModel = getViewModel()

        viewModel.state.observe(this, Observer {
            if (it.photos.isNotEmpty()) {
                Toast.makeText(this, "Has ${it.photos.size} photos", Toast.LENGTH_SHORT).show()
            }
            if (it.error.isNotEmpty()) {
                Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
            }
        })

        val defaultErrorMessage = getString(
            R.string.default_error_message
        )

        viewModel.findPhotos(
            "cat",
            apiKey = getString(R.string.flickr_key),
            perPage = "25",
            defaultErrorMessage = defaultErrorMessage
        )
    }
}
