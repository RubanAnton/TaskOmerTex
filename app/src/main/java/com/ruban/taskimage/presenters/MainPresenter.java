package com.ruban.taskimage.presenters;

import com.ruban.taskimage.model.Image;
import com.ruban.taskimage.network.ImageService;
import com.ruban.taskimage.view.MainActivity;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by antonruban on 10/20/17.
 */


public class MainPresenter {
    MainActivity mainActivity;
    ImageService imageService;

    public MainPresenter(MainActivity view, ImageService service) {

        mainActivity = view;
        imageService = service;
    }

    public void loadPosts() {

        imageService.getApi()
                .getPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Image>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Image> images) {

                        mainActivity.displayPosts(images);
                    }
                });
    }
}