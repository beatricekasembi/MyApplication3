package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.database.Note;

public class ViewNote extends AppCompatActivity {

    int noteId;
    TextView tvTitle;
    TextView tvNoteText;
    String title;
    String notetext;
    Button btnEdit;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getNoteId();


        tvTitle = findViewById(R.id.tvTitle);
        tvNoteText = findViewById(R.id.tvNoteText);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(), "notes", null,1);
                databaseHelper.deleteNote(noteId);
                finish();
//                display when the note is saved

            }
        });
    }

    private void getNoteId(){

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            noteId = extras.getInt("NOTE_ID");
        }

    }

    private void displayNote(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null, 1);
        Note note = databaseHelper.getNoteById(noteId);
        tvTitle.setText(note.getTitle());
        tvNoteText.setText(note.getNote());


    }

    @Override
    protected void onResume() {
        super.onResume();
        displayNote();
    }
}
