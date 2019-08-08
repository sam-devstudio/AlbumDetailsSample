package com.albums.details.sample.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.albums.details.sample.models.AlbumModel;

@Database(entities = {AlbumModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AlbumRecordDAO albumRecordDAO();
}
