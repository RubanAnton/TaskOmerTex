package com.ruban.taskimage.presenters;

import com.ruban.taskimage.model.Image;
import com.ruban.taskimage.network.ImageService;
import com.ruban.taskimage.view.InfoActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by antonruban on 10/20/17.
 */

public class InfoPresenter {

    InfoActivity infoActivity;
    ImageService imageService;

    public InfoPresenter(InfoActivity view, ImageService service) {

        infoActivity = view;
        imageService = service;
    }

    public void loadPost() {

        imageService.getApi()
                .getPhotoID(infoActivity.getPostId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Image>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(Image image) {
                        infoActivity.displayPost(image);
                    }
                });
    }
}