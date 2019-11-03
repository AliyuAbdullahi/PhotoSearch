package com.br.photosearch.domain.dagger.remoterequests

import java.io.IOException

class NoInternetException : IOException() {
    override val message = "No internet connection found"
}