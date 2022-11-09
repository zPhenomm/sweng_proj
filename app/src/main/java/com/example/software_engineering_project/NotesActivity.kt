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


class NotesActivity : AppCompatActivity() {

    companion object {  // this is just typing "static" with extra steps!
        var notes: ArrayList<String> = ArrayList()
    }

    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_activity)

        val sharedPreferences = applicationContext.getSharedPreferences(
            "com.example.software_engineering_project", Context.MODE_PRIVATE)
        val set = sharedPreferences.getStringSet("notes", null) as HashSet<String>?

        if(set == null){
            notes.add("Sample Note")
        }
        else{
            notes = ArrayList(set);
        }

        var list: ListView = findViewById(R.id.list)
        adapter = ArrayAdapter(this, layout.simple_list_item_1, notes)
        list.adapter = adapter


        val btn: Button = findViewById(R.id.newnote)
        btn.setOnClickListener{
            notes.add("")
            adapter.notifyDataSetChanged()
            val intent = Intent(this, NoteEditor::class.java)
            intent.putExtra("noteId", notes.size - 1)
            startActivity(intent)
        }


        list.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(applicationContext, NoteEditor::class.java)
            intent.putExtra("noteId", i)
            startActivity(intent)
        }


        list.setOnItemLongClickListener { adapterView, view, i, l ->
            val itemToDelete = i
            // To delete the data from the App
            AlertDialog.Builder(this@NotesActivity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete?")
                .setPositiveButton("Yep") { dialogInterface, i ->
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


    override fun onResume()
    {
        super.onResume();
        adapter.notifyDataSetChanged()
    }
}