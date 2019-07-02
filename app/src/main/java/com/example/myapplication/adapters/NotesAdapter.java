package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.database.Note;

import java.util.List;

public class NotesAdapter extends ArrayAdapter<Note> {
    public NotesAdapter(Context context, int resource, List<Note> notes) {
        super(context, resource, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_list_item,parent, false);
        }
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvNoteText = convertView.findViewById(R.id.tvnoteText);
        tvTitle.setText(note.getTitle());
        tvNoteText.setText(note.getNote());
        return super.getView(position, convertView, parent);

    }
}
