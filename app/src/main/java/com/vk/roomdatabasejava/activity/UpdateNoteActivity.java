package com.vk.roomdatabasejava.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.vk.roomdatabasejava.database.Note;
import com.vk.roomdatabasejava.database.MyDatabase;
import com.vk.roomdatabasejava.databinding.ActivityUpdateNoteBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateNoteActivity extends AppCompatActivity {

    private static final String TAG = "UpdateNoteActivity";
    private ActivityUpdateNoteBinding binding;
    private Context context;
    private Note note;
    private MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try{
            context = UpdateNoteActivity.this;

            note = (Note) getIntent().getSerializableExtra("NOTE");

            if(note != null){
                binding.etNoteTitleUpdate.setText(note.getTitle());
                binding.etNoteUpdate.setText(note.getNote());
            }

            database = new MyDatabase(context);
            binding.btnUpdateNote.setOnClickListener(v -> {
                updateNote();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateNote() {
        try {
            if (TextUtils.isEmpty(binding.etNoteTitleUpdate.getText().toString())
                    && TextUtils.isEmpty(binding.etNoteUpdate.getText().toString())) return;

            @SuppressLint("SimpleDateFormat")
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            database.updateNote(note.getId(),
                    binding.etNoteTitleUpdate.getText().toString(),
                    binding.etNoteUpdate.getText().toString(),
                    dateFormat.format(cal.getTime()));
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}