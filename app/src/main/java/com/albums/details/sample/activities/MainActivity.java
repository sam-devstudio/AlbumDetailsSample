package com.albums.details.sample.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.albums.details.sample.R;
import com.albums.details.sample.adapters.AlbumsRVAdapter;
import com.albums.details.sample.databinding.ActivityMainBinding;
import com.albums.details.sample.models.AlbumModel;
import com.albums.details.sample.viewmodels.AlbumsViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private AlbumsRVAdapter albumsRVAdapter;
    private RecyclerView rvAlbums;
    private AlbumsViewModel albumsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        albumsViewModel = ViewModelProviders.of(this).get(AlbumsViewModel.class);
        rvAlbums = activityMainBinding.rvAlbums;
        rvAlbums.setLayoutManager(new LinearLayoutManager(this));
        rvAlbums.setHasFixedSize(true);

        albumsRVAdapter = new AlbumsRVAdapter();
        rvAlbums.setAdapter(albumsRVAdapter);

        fetchAllAlbums();
    }

    private void fetchAllAlbums(){
        albumsViewModel.getAllAlbumsData().observe(this, new Observer<ArrayList<AlbumModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<AlbumModel> albumModelList) {
                albumsRVAdapter.setListData(albumModelList);
                activityMainBinding.loadingBar.setVisibility(View.GONE);
            }
        });
    }
}
