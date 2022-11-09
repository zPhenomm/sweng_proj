package com.example.software_engineering_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class NoteEditor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_notes)

        val view: EditText = findViewById(R.id.editNote)
        val intent: Intent = intent

        // Accessing the data using key and value
        var noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1) {
            view.setText(NotesActivity.notes[noteId]);
        }

        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                NotesActivity.notes[noteId] = charSequence.toString()
                // Creating Object of SharedPreferences to store data in the phone
                val sharedPreferences = applicationContext.getSharedPreferences(
                    "com.example.software_engineering_project", Context.MODE_PRIVATE)
                val set: HashSet<String> = HashSet(NotesActivity.notes)
                sharedPreferences.edit().putStringSet("notes", set).apply()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
}