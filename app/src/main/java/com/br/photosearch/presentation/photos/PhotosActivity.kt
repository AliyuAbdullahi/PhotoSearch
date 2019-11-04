package com.br.photosearch.presentation.photos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.br.photosearch.R
import com.br.photosearch.domain.models.Photo
import com.br.photosearch.domain.models.large
import com.br.photosearch.presentation.base.view.BaseActivity
import com.br.photosearch.presentation.photodetail.PhotoDetailsActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_photos.*
import kotlinx.android.synthetic.main.fragment_photos.photoList
import kotlinx.android.synthetic.main.fragment_photos.searchView
import java.util.concurrent.TimeUnit

class PhotosActivity : BaseActivity(), PhotosAdapter.Interaction {

    private lateinit var viewModel: PhotosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        setSupportActionBar(toolbar)
        viewModel = getViewModel()

        val defaultErrorMessage = getString(
            R.string.default_error_message
        )

        searchForPhotos("cities", defaultErrorMessage)

        val photosAdapter = PhotosAdapter(this)
        with(photoList) {
            layoutManager = GridLayoutManager(this@PhotosActivity, 2)
            adapter = photosAdapter
        }

        viewModel.state.observe(this, Observer {
            photosAdapter.submitList(it.photos)
            if(it.error.isNotEmpty()) {
                Snackbar.make(root, it.error, Snackbar.LENGTH_SHORT).show()
            }
        })

        viewModel.disposable.addAll(
            RxSearch.fromView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    searchForPhotos(it, defaultErrorMessage)
                }
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.photos_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.savedPhotos -> {
                //open saved photos
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(
        position: Int,
        item: Photo,
        imageView: ImageView,
        textView: TextView
    ) {
        navigateToPhotoDetails(item, imageView, textView)
    }

    private fun navigateToPhotoDetails(photo: Photo, imageView: ImageView, textView: TextView) {
        val transitionPairForImage = Pair.create(imageView as View, getString(R.string.photo))
        val transitionPairForPhoto = Pair.create(textView as View, getString(R.string.photo_title))

        val activityCompatOption =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                transitionPairForImage,
                transitionPairForPhoto
            )
        val photoDetailsIntent = Intent(this, PhotoDetailsActivity::class.java)
        photoDetailsIntent.putExtra(PHOTO_DETAILS_KEY, photo)
        photoDetailsIntent.putExtra(PHOTO_URL, photo.large())
        startActivity(photoDetailsIntent, activityCompatOption.toBundle())
    }

    private fun searchForPhotos(filter: String, defaultErrorMessage: String) {
        viewModel.findPhotos(
            filter,
            apiKey = getString(R.string.flickr_key),
            defaultErrorMessage = defaultErrorMessage
        )
    }

    companion object {
        const val PHOTO_DETAILS_KEY = "photo_details"
        const val PHOTO_URL = "photo_url"
    }
}
