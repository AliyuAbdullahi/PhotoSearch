package com.br.photosearch.domain.services;

import com.br.photosearch.domain.models.PhotosResponse;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotosService {
    @GET("services/rest/")
    Single<Response<PhotosResponse>> getPhotos(@Query("tags") String searchTag,
                                               @Query("api_key") String apiKey,
                                               @Query("per_page") String perPage,
                                               @Query("method") String method,
                                               @Query("format") String format,
                                               @Query("nojsoncallback") String noJsonCallback);
}
