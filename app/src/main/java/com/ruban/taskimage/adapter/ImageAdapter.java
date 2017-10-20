package com.ruban.taskimage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ruban.taskimage.R;
import com.ruban.taskimage.model.Image;

import java.util.ArrayList;

/**
 * Created by antonruban on 10/20/17.
 */
public class ImageAdapter extends ArrayAdapter<Image> {

    public ImageAdapter(Context ctx, ArrayList<Image> images) {
        super(ctx, 0, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Image image = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_item, parent, false);

        ImageView imgView = convertView.findViewById(R.id.imageView);
        Glide.with(getContext()).load(image.url).into(imgView);
        return convertView;
    }
}