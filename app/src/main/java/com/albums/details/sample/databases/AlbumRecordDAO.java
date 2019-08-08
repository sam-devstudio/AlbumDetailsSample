package com.albums.details.sample.databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.albums.details.sample.models.AlbumModel;

import java.util.List;

@Dao
public interface AlbumRecordDAO {
    @Query("SELECT * FROM AlbumModel")
    List<AlbumModel> getAll();

    @Insert
    void insert(AlbumModel albumModel);

    @Delete
    void delete(AlbumModel albumModel);

    @Update
    void update(AlbumModel albumModel);
}
