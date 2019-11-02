package com.br.photosearch.domain.services;

import com.br.photosearch.domain.models.PhotosResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotosService {
    @GET(".")
    Single<PhotosResponse> getPhotos(@Query("tags") String searchTag);
}
