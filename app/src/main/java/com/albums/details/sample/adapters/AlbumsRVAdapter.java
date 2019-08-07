package com.albums.details.sample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albums.details.sample.R;
import com.albums.details.sample.databinding.AlbumsDataRowBinding;
import com.albums.details.sample.databinding.AlbumsIdRowBinding;
import com.albums.details.sample.helpers.Constants;
import com.albums.details.sample.models.AlbumModel;

import java.util.ArrayList;

public class AlbumsRVAdapter extends RecyclerView.Adapter {

    private ArrayList<AlbumModel> listData;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constants.TYPE_DATA) {
            AlbumsDataRowBinding albumsDataRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.albums_data_row, parent, false);

            return new AlbumDetailsHolder(albumsDataRowBinding);
        } else {
            AlbumsIdRowBinding albumsIdRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.albums_id_row, parent, false);

            return new AlbumIDHolder(albumsIdRowBinding);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof AlbumDetailsHolder){
            ((AlbumDetailsHolder)holder).albumsDataRowBinding.setAlbum(listData.get(position));
        }else{
            ((AlbumIDHolder)holder).albumsIdRowBinding.setAlbum(listData.get(position));
        }

    }

    @Override
    public int getItemCount() {
        if(listData != null){
            return listData.size();
        }
        return 0;
    }

    public static class AlbumDetailsHolder extends RecyclerView.ViewHolder {

        public AlbumsDataRowBinding albumsDataRowBinding;

        public AlbumDetailsHolder(AlbumsDataRowBinding albumsDataRowBinding) {
            super(albumsDataRowBinding.getRoot());
            this.albumsDataRowBinding = albumsDataRowBinding;
        }
    }

    public static class AlbumIDHolder extends RecyclerView.ViewHolder {

        public AlbumsIdRowBinding albumsIdRowBinding;

        public AlbumIDHolder(AlbumsIdRowBinding albumsIdRowBinding) {
            super(albumsIdRowBinding.getRoot());

            this.albumsIdRowBinding = albumsIdRowBinding;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(listData != null){
            return listData.get(position).getType();
        }

        return super.getItemViewType(position);
    }

    public void setListData(ArrayList<AlbumModel> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }
}
