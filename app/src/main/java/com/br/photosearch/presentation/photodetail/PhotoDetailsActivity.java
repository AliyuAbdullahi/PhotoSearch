package com.br.photosearch.presentation.photodetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.br.photosearch.R;
import com.br.photosearch.domain.models.Photo;
import com.br.photosearch.presentation.base.view.BaseActivity;
import com.br.photosearch.presentation.photos.PhotosActivity;
import com.bumptech.glide.Glide;

import static com.br.photosearch.presentation.photos.PhotosActivity.PHOTO_URL;

public class PhotoDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        Toolbar toolbar = findViewById(R.id.activityDetailsToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ImageView photoImage = findViewById(R.id.photoImage);
        TextView photoTitle = toolbar.findViewById(R.id.photoTitle);
        Intent data = getIntent();

        if (data.hasExtra(PhotosActivity.PHOTO_DETAILS_KEY)) {
            Photo photo = data.getParcelableExtra(PhotosActivity.PHOTO_DETAILS_KEY);
            if (photo != null) {
                photoTitle.setText(photo.getTitle());
            }
        }

        if (data.hasExtra(PHOTO_URL)) {
            String photoUrl = data.getStringExtra(PHOTO_URL);
            Glide.with(this).load(photoUrl)
                    .placeholder(R.drawable.ic_photo_size_select_actual_black_24dp)
                    .error(R.drawable.ic_photo_size_select_actual_black_24dp)
                    .into(photoImage);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
