package com.example.newstudent.studentList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.newstudent.R
import com.example.newstudent.db.Student
import kotlinx.android.synthetic.main.list_student_item.view.*

class studentListAdapter(var data: List<Student>): RecyclerView.Adapter<studentListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_student_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.name_text)
        val cgpa:TextView = itemView.findViewById(R.id.cgpa_text)
        val roll:TextView = itemView.findViewById(R.id.roll_no_text)

        fun bind(item:Student){
            name.text = item.name
            cgpa.text = item.cgpa.toString()
            roll.text = item.rollNo.toString()
        }
    }
}
