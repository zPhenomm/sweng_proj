/**
 * Activity for writing and saving important information
 *
 * @author Max Hannawald
 */
package com.example.software_engineering_project

import android.R.layout
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


/**
 * Notes activity
 * New notes can be created or deleted
 * Notes are stored in sharedPreferences on the device so they persist after closing the app
 *
 * @constructor Create new notes activity
 */
class NotesActivity : AppCompatActivity() {
    /** The list of notes */
    companion object {  // this is just typing "static" with extra steps!
        var notes: ArrayList<String> = ArrayList()
    }
    /** The adapter object to link the list to a view */
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_activity)

        // loads existing notes from the device storage
        val sharedPreferences = applicationContext.getSharedPreferences(
            "com.example.software_engineering_project", Context.MODE_PRIVATE)
        val set = sharedPreferences.getStringSet("notes", null) as HashSet<String>?

        if(set == null){
            notes.add("Sample Note")
        }
        else{
            notes = ArrayList(set)
        }

        val list: ListView = findViewById(R.id.list)
        adapter = ArrayAdapter(this, layout.simple_list_item_1, notes)
        list.adapter = adapter  // maps adapter to list

        // create new note on button press and start the editor
        val btn: Button = findViewById(R.id.newnote)
        btn.setOnClickListener{
            notes.add("")
            adapter.notifyDataSetChanged()
            val intent = Intent(this, NoteEditor::class.java)
            intent.putExtra("noteId", notes.size - 1)
            startActivity(intent)
        }

        // edit existing note on press of list element and start the editor
        list.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(applicationContext, NoteEditor::class.java)
            intent.putExtra("noteId", i)
            startActivity(intent)
        }

        // delete existing note on long press of list element
        list.setOnItemLongClickListener { adapterView, view, i, l ->
            val itemToDelete = i
            // ask for confirmation
            AlertDialog.Builder(this@NotesActivity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete?")
                .setPositiveButton("Yep") { dialogInterface, i ->
                    // on confirmation delete from list AND from device storage
                    notes.removeAt(itemToDelete)
                    adapter.notifyDataSetChanged()
                    val sharedPreferences = applicationContext.getSharedPreferences(
                        "com.example.software_engineering_project", Context.MODE_PRIVATE)
                    val set: HashSet<String> = HashSet(notes)
                    sharedPreferences.edit().putStringSet("notes", set).apply()
                }.setNegativeButton("Nah", null).show()
            true
        }
    }

    // when returning from editor update list
    override fun onResume()
    {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
