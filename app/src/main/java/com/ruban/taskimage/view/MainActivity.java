package com.ruban.taskimage.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ruban.taskimage.R;
import com.ruban.taskimage.adapter.ImageAdapter;
import com.ruban.taskimage.model.Image;
import com.ruban.taskimage.network.ImageService;
import com.ruban.taskimage.presenters.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.listViewImages)
    ListView listView;

    ImageAdapter imageAdapter;

    MainPresenter mainPresenter;
    ImageService imageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        ArrayList<Image> dummyPosts = new ArrayList<Image>();
        imageAdapter = new ImageAdapter(this, dummyPosts);
        listView.setAdapter(imageAdapter);

        imageService = new ImageService();
        mainPresenter = new MainPresenter(this, imageService);
        mainPresenter.loadPosts();
    }

    public void displayPosts(List<Image> posts) {

        imageAdapter.clear();
        imageAdapter.addAll(posts);
        imageAdapter.notifyDataSetInvalidated();
    }

  @OnItemClick(R.id.listViewImages)
    public void selectIMG(int position){
      Image img = imageAdapter.getItem(position);
      int imageID = img.id;
      Intent intent = new Intent(this,InfoActivity.class);
      intent.putExtra("imageID",img.id);
      startActivity(intent);
  }
}
