package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes: List<Note>, context: Context):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val db:NoteDatabaseHelper = NoteDatabaseHelper(context)

    class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val titleTxtV: TextView = itemView.findViewById(R.id.titleTxtV)
        val contentTxyV: TextView = itemView.findViewById(R.id.contentTxtV)
        /*val updateBtn: ImageView = itemView.findViewById(R.id.updateBtn)
        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn)*/
        val card: CardView = itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTxtV.text = note.title
        holder.contentTxyV.text = note.content

/*holder.updateBtn.setOnClickListener {
    val intent= Intent(holder.itemView.context, UpdateActivity::class.java).apply {
        putExtra("note_id", note.id)
    }
    holder.itemView.context.startActivity(intent)
}

holder.deleteBtn.setOnClickListener {
    db.deteNote(note.id)
    refreshData(db.getAllNotes())
    Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
}*/

holder.card.setOnClickListener {
    val intent= Intent(holder.itemView.context, UpdateActivity::class.java).apply {
        putExtra("note_id", note.id)
    }
    holder.itemView.context.startActivity(intent)
}
holder.card.setOnLongClickListener {
    db.deleteNote(note.id)
    refreshData(db.getAllNotes())
    Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
    true

}

}

fun refreshData(newNotes: List<Note>){
notes = newNotes
notifyDataSetChanged()
}

}