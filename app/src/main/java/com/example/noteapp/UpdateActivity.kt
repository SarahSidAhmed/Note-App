package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId==-1){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.updateTitleEditTxt.setText(note.title)
        binding.updateContentEditTxt.setText(note.content)
        binding.updateSaveBtn.setOnClickListener {
            val newTitle = binding.updateTitleEditTxt.text.toString()
            val newContent = binding.updateContentEditTxt.text.toString()
            val updateNote = Note(noteId, newTitle, newContent)
            if (newTitle.isNotEmpty() || newContent.isNotEmpty()) {
                db.updateNote(updateNote)
                finish()
                Toast.makeText(this@UpdateActivity, "Changes Saved", Toast.LENGTH_SHORT).show()
            }else{
                db.deleteNote(noteId)
                Toast.makeText(this@UpdateActivity, "Deleted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }
}