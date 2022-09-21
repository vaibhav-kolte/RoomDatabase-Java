package com.vk.roomdatabasejava.adapter;


import static com.vk.roomdatabasejava.R.*;

import android.content.Context;
import android.content.Intent;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vk.roomdatabasejava.database.Note;
import com.vk.roomdatabasejava.R;
import com.vk.roomdatabasejava.activity.UpdateNoteActivity;
import com.vk.roomdatabasejava.database.MyDatabase;

import java.io.Serializable;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private final Context context;
    private final List<Note> notesList;
    private boolean isEnable;
    private Note selectedNode;

    public NoteAdapter(Context context, List<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
        isEnable = false;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View convertView = layoutInflater.inflate(layout.note_layout, parent, false);

            return new NoteViewHolder(convertView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        try {
            Note note = notesList.get(position);

            holder.noteTitle.setText(note.getTitle());
            holder.note.setText(note.getNote());
            holder.dateTime.setText(note.getDateTime());

            holder.noteView.setOnClickListener(v -> {
                if(isEnable) return;
                Intent intent = new Intent(context, UpdateNoteActivity.class);
                intent.putExtra("NOTE", (Serializable) note);
                context.startActivity(intent);
            });

            holder.noteView.setOnLongClickListener(v -> {
                if (!isEnable) {

                    ActionMode.Callback callback = new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                            MenuInflater menuInflater = mode.getMenuInflater();

                            menuInflater.inflate(R.menu.menu, menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            clickItem(holder);
                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                            try {
                                int id = item.getItemId();
                                switch (id) {
                                    case R.id.note_delete:
                                        new MyDatabase(context).deleteNode(selectedNode.getId());

                                        break;
                                    default:

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return false;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {
                            try {
                                clickItem(holder);
                                selectedNode = null;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    ((AppCompatActivity) context).startActionMode(callback);
                }
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clickItem(NoteViewHolder holder) {
        try {
            if(!isEnable) {
                isEnable = true;
                selectedNode = notesList.get(holder.getAdapterPosition());
                holder.noteView.setBackground(context.getDrawable(color.purple_200));
            }else{
                isEnable = false;
                holder.noteView.setBackground(context.getDrawable(color.white));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle, note, dateTime;
        CardView noteView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                noteTitle = itemView.findViewById(id.tvTitle);
                note = itemView.findViewById(id.tvNote);
                dateTime = itemView.findViewById(id.tvDateTime);
                noteView = itemView.findViewById(id.noteView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
