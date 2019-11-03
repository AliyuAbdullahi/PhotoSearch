package com.br.photosearch.domain.dagger.remoterequests

import android.app.Application
import com.br.photosearch.BuildConfig
import com.br.photosearch.domain.services.PhotosService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(interceptor)
        okHttpClientBuilder.addInterceptor(ConnectivityInterceptor(application))
        okHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(10, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(10, TimeUnit.SECONDS)

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(okHttpClientBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun providePhotoService(retrofit: Retrofit): PhotosService {
        return retrofit.create(PhotosService::class.java)
    }
}