package com.vk.roomdatabasejava.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;

import com.vk.roomdatabasejava.database.MyDatabase;
import com.vk.roomdatabasejava.databinding.ActivityAddNoteBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    private static final String TAG = "AddNoteActivity";
    private ActivityAddNoteBinding binding;
    private MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        database = new MyDatabase(AddNoteActivity.this);
        binding.btnSaveNote.setOnClickListener(v -> {
            saveNode();
        });
    }

    private void saveNode() {
        try {
            if (TextUtils.isEmpty(binding.etNoteTitle.getText().toString())
                    && TextUtils.isEmpty(binding.etNote.getText().toString())) return;

            @SuppressLint("SimpleDateFormat")
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();

            database.storeData(binding.etNoteTitle.getText().toString(),
                    binding.etNote.getText().toString(),
                    dateFormat.format(cal.getTime()));
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}