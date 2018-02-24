package androidtest.com.androidtest;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import androidtest.com.androidtest.Adapter.SongAdapter;
import androidtest.com.androidtest.Model.SongResponse;
import androidtest.com.androidtest.Presenter.SongPresenter;
import androidtest.com.androidtest.Utils.Util;
import androidtest.com.androidtest.interfaces.SongPresenterListener;
import androidtest.com.androidtest.interfaces.onItemClickListener;

public class MainActivity extends AppCompatActivity implements SongPresenterListener, onItemClickListener {

    private String TAG = MainActivity.class.getName();

    private SongPresenter songPresenter;
    private List<SongResponse.SongModel> songList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar mProgressBar;
    private SongAdapter mAdapter;
    private String searchSong = "Michael Jakson";//when first time data load

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songPresenter = new SongPresenter(this, this);
        songPresenter.searchSong(searchSong);

        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.songslist);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        songList = new ArrayList<>();
        mAdapter = new SongAdapter(this, songList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void songsReady(List<SongResponse.SongModel> songs) {

        for (SongResponse.SongModel song : songs) {
            Log.i("RETROFIT : ArtistName", song.getArtistName() + "\n"
                    + " - TrackName:  " + song.getTrackName() + " \n"
                    + " - URL: " + song.getArtworkUrl60());
        }
        songList = songs;
        displayData();


    }

    private void displayData() {
        recyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mAdapter = new SongAdapter(this, songList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void songErrorSenario(String msg) {
        Util.showToast(this, msg);
        //In error case of error senario old data should be displayed again
        displayData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(final SearchView searchView, final Menu mMenu) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "Search query : " + query);

                if (Util.isNetworkAvailable(MainActivity.this)) {
                    showProgressView();
                    songPresenter.searchSong(query);
                    searchView.setQuery("", false);
                    searchView.setIconified(true);

                } else {
                    Util.showToast(MainActivity.this, getString(R.string.msg_no_internet));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        searchView.setIconified(true);
    }

    private void showProgressView() {
        recyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void itemDetailClick(SongResponse.SongModel mSongModel) {
        Log.i("RETROFIT : ArtistName", mSongModel.getArtistName() + "\n"
                + " - TrackName:  " + mSongModel.getTrackName() + " \n"
                + " - URL: " + mSongModel.getArtworkUrl60() + " \n"
                + " - album: " + mSongModel.getCollectionName() + " \n"
                + " - price: " + mSongModel.getCollectionPrice() + " \n"
                + " - price: " + mSongModel.getReleaseDate());
        Intent intent = new Intent(this, SongDetailActivity.class);
        intent.putExtra(SongDetailActivity.ARG_ITEM_OBJECT, mSongModel);
        startActivity(intent);
    }
}