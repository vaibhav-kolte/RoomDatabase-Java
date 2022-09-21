package com.vk.roomdatabasejava.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")
public class Note implements Serializable {
    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo(name = "note_title")
    public String title;

    @ColumnInfo(name = "note")
    public String note;

    @ColumnInfo(name = "date_time")
    public String dateTime;

    @Ignore
    public Note(int id, String title, String note, String dateTime) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.dateTime = dateTime;
    }

    public Note(String title, String note, String dateTime) {
        this.title = title;
        this.note = note;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}