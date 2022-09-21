package com.vk.roomdatabasejava.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vk.roomdatabasejava.database.Note;

import java.util.List;


@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Query("SELECT EXISTS(SELECT * FROM notes WHERE id =:noteId )")
    Boolean is_exits(int noteId);

    @Query("SELECT * FROM notes")
    List<Note> getAllNote();

    @Query("SELECT * FROM notes WHERE id =:noteId")
    Note getNote(int noteId);

    @Query("UPDATE notes set note_title =:title, note =:note, date_time =:date_time WHERE id =:noteId")
    void updateNote(int noteId,String title,String note,String date_time);

    @Query("DELETE FROM notes WHERE id =:noteId")
    void deleteNote(int noteId);

}