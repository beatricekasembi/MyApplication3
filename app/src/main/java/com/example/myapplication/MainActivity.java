package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.adapters.NotesAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.database.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    TextView BeatyWork;
    Button btnViewNote;
    ListView listViewNames;
    List<Note> noteList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),AddNoteActivity.class));
                //startActivity(new Intent(getBaseContext(),TrialActivity.class));
            }
        });


        listViewNames = findViewById(R.id.listViewNames);
        displayNote();

//        displayNames();

//        BeatyWork = (TextView) findViewById(R.id.BeatyWork);
//        BeatyWork.setText("The quick brown fox jumps over the lazy dog");
//
//        btnViewNote = findViewById(R.id.btnViewNote);
//        btnViewNote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(),ViewNote.class));
//            }
//        });

    }

    private void displayNote(){

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null,1);
        noteList = new ArrayList<Note>();
        noteList = databaseHelper.getNotes();
        Log.d("notes", "My note List has " + noteList.size() + " notes");
        NotesAdapter notesAdapter = new NotesAdapter(getBaseContext(), 0, noteList);
        listViewNames.setAdapter(notesAdapter);
        listViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Note note = noteList.get(position);
                Intent intent = new Intent(getBaseContext(),ViewNote.class);
                intent.putExtra("NOTE_ID", note.getId());
                startActivity(intent);

            }
        });

    }

    private void displayNames (){
        List<String> namesList = new ArrayList<String>();
        namesList.add("Beatrice Kasembi");
        namesList.add("Joy Wanja");
        namesList.add("Irene Nyambura");
        namesList.add("Catherine Watiri");
        namesList.add("Everlyne Mueni");
        namesList.add("Dennis Nyoike");
        namesList.add("James Mutua");
        namesList.add("Alice Wanja");
        namesList.add("Michael Wachira");
        namesList.add("Jasmine Wanja");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesList
        );
        listViewNames.setAdapter(arrayAdapter);

    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
