package com.albums.details.sample.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.albums.details.sample.models.AlbumModel;
import com.albums.details.sample.repositories.AlbumRepository;

import java.util.ArrayList;

public class AlbumsViewModel extends AndroidViewModel {

    private AlbumRepository albumRepository;

    public AlbumsViewModel(@NonNull Application application) {
        super(application);
        albumRepository = new AlbumRepository(getApplication().getApplicationContext());
    }

    public MutableLiveData<ArrayList<AlbumModel>> getAllAlbumsData(){
        return albumRepository.getListMutableLiveData();
    }
}
