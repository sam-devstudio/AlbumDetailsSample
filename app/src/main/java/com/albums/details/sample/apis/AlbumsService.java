package com.albums.details.sample.apis;

import com.albums.details.sample.models.AlbumModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumsService {

    @GET("photos")
    Call<ArrayList<AlbumModel>> getAlbums();

}
