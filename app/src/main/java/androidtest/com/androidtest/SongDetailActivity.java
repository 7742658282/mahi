/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidtest.com.androidtest;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import androidtest.com.androidtest.Model.SongResponse;
import androidtest.com.androidtest.Utils.Util;
import de.hdodenhof.circleimageview.CircleImageView;

public class SongDetailActivity extends AppCompatActivity {

    public static final String ARG_ITEM_OBJECT = "ARG_ITEM_OBJECT";
    private SongResponse.SongModel mSongModel;
    private CircleImageView mArtworkImg;
    private ImageView mArtworkImgbig;
    private TextView trackNameTxt, artistTxt, priceTxt, releaseDateTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent != null)
            mSongModel = (SongResponse.SongModel) intent.getSerializableExtra(ARG_ITEM_OBJECT);
        else
            finish();

        initToolBar();
        init();
        loadData();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mSongModel.getCollectionName());
    }

    private void init() {
        mArtworkImg = (CircleImageView) findViewById(R.id.artworkImg);
        mArtworkImgbig = (ImageView) findViewById(R.id.artworkImgbig);
        trackNameTxt = (TextView) findViewById(R.id.track_name);
        artistTxt = (TextView) findViewById(R.id.artist_name);
        priceTxt = (TextView) findViewById(R.id.price);
        releaseDateTxt = (TextView) findViewById(R.id.releasedate);
    }

    private void loadData() {
        Glide.with(this).load(mSongModel.getArtworkUrl100()).centerCrop().into(mArtworkImg);
        Glide.with(this).load(mSongModel.getArtworkUrl100()).centerCrop().into(mArtworkImgbig);
        trackNameTxt.setText(getString(R.string.title_track) + " : " + mSongModel.getTrackName());
        artistTxt.setText(getString(R.string.title_artist) + " : " + mSongModel.getArtistName());
        priceTxt.setText(getString(R.string.title_price) + " : " + getString(R.string.symbol_dollar) + mSongModel.getTrackPrice());
        releaseDateTxt.setText(getString(R.string.title_releasedate) + " : " + Util.dateFormat(mSongModel.getReleaseDate()));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
