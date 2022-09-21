package com.vk.roomdatabasejava.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract NoteDao noteDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "note_db").allowMainThreadQueries().build();
        }
        return instance;
    }
}