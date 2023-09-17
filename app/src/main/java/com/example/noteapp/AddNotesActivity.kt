package com.example.noteapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        binding.saveBtn.setOnClickListener {

            val title = binding.titleEditTxt.text.toString()
            val content = binding.contentEditTxt.text.toString()

            if (title.isNotEmpty() || content.isNotEmpty()){
                val note = Note(0, title, content)
                db.insertNote(note)
                finish()
                Toast.makeText(this@AddNotesActivity, "Note Saved", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@AddNotesActivity, "Note Empty!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}