package com.br.photosearch.domain.dagger.remoterequests

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response
import android.os.Build


class ConnectivityInterceptor(private val app: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isNetworkAvailable(app.applicationContext)) {
            throw NoInternetException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context): Boolean {

        try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT > 22) {
                val activeNetwork = connectivityManager.activeNetwork ?: return false
                val capabilities =
                    connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo ?: return false
                activeNetworkInfo.isConnected && (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
                        || activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}