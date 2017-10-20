package com.ruban.taskimage.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ruban.taskimage.R;
import com.ruban.taskimage.model.Image;
import com.ruban.taskimage.network.ImageService;
import com.ruban.taskimage.presenters.InfoPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class InfoActivity extends ActionBarActivity {

    @InjectView(R.id.textViewTitle)
    TextView title;

    @InjectView(R.id.textViewID)
    TextView id;

    @InjectView(R.id.textViewAlbumID)
    TextView albumID;

    @InjectView(R.id.textViewURL)
    TextView url;

    @InjectView(R.id.textViewThumbnailUrl)
    TextView thumbnailUrl;

    InfoPresenter infoPresenter;
    ImageService imageService;

    protected int imgID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        imgID = intent.getIntExtra("imageID", 0);


        imageService = new ImageService();
        infoPresenter = new InfoPresenter(this, imageService);
        infoPresenter.loadPost();
    }

    public int getPostId() {
        return imgID;
    }

    public void displayPost(Image image) {
        id.setText(image.id);
        albumID.setText(image.albumId);
        title.setText(image.title);
        url.setText(image.url);
        thumbnailUrl.setText(image.thumbnailUrl);
    }


}
