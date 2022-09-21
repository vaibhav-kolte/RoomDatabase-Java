package com.vk.roomdatabasejava.database;

import android.content.Context;

import java.util.List;

public class MyDatabase {

    private AppDatabase db;
    private NoteDao noteDao;

    public MyDatabase(Context _context) {
        try{
            db = AppDatabase.getInstance(_context);
            noteDao = db.noteDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Note> showAllData() {
        try {
            return noteDao.getAllNote();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void storeData(String title, String note,String date_time) {
        try {
            noteDao.insert(new Note(title,note,date_time));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNote(int noteId,String title, String note,String date_time){
        try{
            noteDao.updateNote(noteId,title,note,date_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNode(int noteId){
        try{
            noteDao.deleteNote(noteId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
