package com.albums.details.sample.databases;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {

    private static DatabaseClient databaseClient;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {

        //AlbumsData is the name of the database
        appDatabase = Room.databaseBuilder(context,AppDatabase.class,"AlbumsData").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (databaseClient == null) {
            databaseClient = new DatabaseClient(mCtx);
        }
        return databaseClient;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}