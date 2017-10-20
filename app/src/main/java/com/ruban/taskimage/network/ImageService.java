package com.ruban.taskimage.network;

import com.ruban.taskimage.model.Image;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by antonruban on 10/20/17.
 */

public class ImageService {
    private static final String BASE_STRING = "http://jsonplaceholder.typicode.com";
    private ImageApi imgAPI;

    public ImageService() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_STRING)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        imgAPI = restAdapter.create(ImageApi.class);
    }

    public ImageApi getApi() {
        return imgAPI;
    }

    public interface ImageApi {

        @GET("/photos")
        Observable<List<Image>>
        getPhotos();

        @GET("/photos/{id}")
        Observable<Image>
        getPhotoID(@Path("id") int postId);
    }
}