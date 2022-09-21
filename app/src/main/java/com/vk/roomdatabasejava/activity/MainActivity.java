package com.vk.roomdatabasejava.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.vk.roomdatabasejava.adapter.NoteAdapter;
import com.vk.roomdatabasejava.database.MyDatabase;
import com.vk.roomdatabasejava.database.Note;
import com.vk.roomdatabasejava.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private Context context;
    private MyDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = MainActivity.this;
        database = new MyDatabase(context);

        binding.fabAddButton.setOnClickListener(v -> {
            startActivity(new Intent(context, AddNoteActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNote();
    }

    private void getNote() {
        try {
            List<Note> notes = database.showAllData();
            if (notes.size() <= 0) return;
            LinearLayoutManager llm = new LinearLayoutManager(context);
            binding.noteRV.setHasFixedSize(true);
            binding.noteRV.setLayoutManager(llm);
            binding.noteRV.setAdapter(new NoteAdapter(context, notes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}