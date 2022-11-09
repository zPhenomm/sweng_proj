/**
 * Activity for editing new notes
 *
 * @author Max Hannawald
 */
package com.example.software_engineering_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


/**
 * Editor class.
 * @constructor Create new editor activity
 */
class NoteEditor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_notes)

        val view: EditText = findViewById(R.id.editNote)
        val intent: Intent = intent

        // set existing text into the view if an existing note is edited
        val noteId = intent.getIntExtra("noteId", -1)
        if (noteId != -1) {
            view.setText(NotesActivity.notes[noteId])
        }

        // after making changes save note in list AND storage
        view.addTextChangedListener(object : TextWatcher {
            // although not used TextWatcher requires this constructor
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                NotesActivity.notes[noteId] = charSequence.toString()
                val sharedPreferences = applicationContext.getSharedPreferences(
                    "com.example.software_engineering_project", Context.MODE_PRIVATE)
                val set: HashSet<String> = HashSet(NotesActivity.notes)
                sharedPreferences.edit().putStringSet("notes", set).apply()
            }
            // although not used TextWatcher requires this constructor
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
}
