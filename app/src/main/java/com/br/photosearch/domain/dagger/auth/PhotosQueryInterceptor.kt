package com.br.photosearch.domain.dagger.auth

import android.app.Application
import com.br.photosearch.R
import okhttp3.Interceptor
import okhttp3.Response

class PhotosQueryInterceptor(private val app: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("method", "flickr.photos.search")
            .addQueryParameter("format", "json")
            .addQueryParameter("nojsoncallback", "1")
            .addQueryParameter("api_key", app.getString(R.string.app_name))
            .build()
        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}