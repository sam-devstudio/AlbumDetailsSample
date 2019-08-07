package com.albums.details.sample.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.albums.details.sample.apis.AlbumsService;
import com.albums.details.sample.apis.ApiClient;
import com.albums.details.sample.helpers.Constants;
import com.albums.details.sample.models.AlbumModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepository {

    private MutableLiveData<ArrayList<AlbumModel>> listMutableLiveData = new MutableLiveData<>();
    private ArrayList<AlbumModel> listData = new ArrayList<>();

    public MutableLiveData<ArrayList<AlbumModel>> getListMutableLiveData() {

        if(listData.size() <= 0) {
            final AlbumsService albumsService = ApiClient.getService();

            Call<ArrayList<AlbumModel>> call = albumsService.getAlbums();
            call.enqueue(new Callback<ArrayList<AlbumModel>>() {


                @Override
                public void onResponse(Call<ArrayList<AlbumModel>> call, Response<ArrayList<AlbumModel>> response) {
                    Log.d("tagData","Success");
                    listData = response.body();
                    if (listData != null) {
                        listMutableLiveData.setValue(orderData(listData));
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<AlbumModel>> call, Throwable t) {
                    Log.d("tagData","Error while fetching data");
                }

            });
        }

        return listMutableLiveData;
    }


    private ArrayList<AlbumModel> orderData(ArrayList<AlbumModel> listDataFetched){

        //It is supposed that ID's are ordered

        Long previousAlbumId = -1L;
        ArrayList<AlbumModel> updatedList = new ArrayList<>();

        for(AlbumModel albumModel : listDataFetched){
            if(previousAlbumId != albumModel.getAlbumId()){
                //Creating an ID type object in list for Grouping of data in view
                //Creating a new Item and seetting its data
                AlbumModel idAlbumModel = new AlbumModel();
                idAlbumModel.setAlbumId(albumModel.getAlbumId());
                idAlbumModel.setTitle(albumModel.getTitle());
                idAlbumModel.setType(Constants.TYPE_ID);
                idAlbumModel.setUrl(albumModel.getUrl());

                //Pushing ID item
                updatedList.add(idAlbumModel);
                //Pushing  Data Item
                updatedList.add(albumModel);

                //Updating ID
                previousAlbumId = albumModel.getAlbumId();
            }else{
                //Just pushing data as id is already added
                updatedList.add(albumModel);
            }
        }

        return updatedList;
    }
}
