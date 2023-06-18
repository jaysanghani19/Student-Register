package com.example.studentregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.TextView
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregister.database.Student

class RecycleViewAdapter(private val clickListener: (Student) -> Unit) : RecyclerView.Adapter<RecylerViewHolder>(){


    private val studentList = ArrayList<Student>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecylerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.recycler_view_layout,parent,false)
        return RecylerViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: RecylerViewHolder, position: Int) {
        holder.bind(studentList[position] , clickListener)
    }

    fun setList(students : List<Student>)
    {
        studentList.clear()
        studentList.addAll(students)
    }

}

class RecylerViewHolder(private val view : View) : RecyclerView.ViewHolder(view)
{
    fun bind(student: Student, clickListener: (Student) -> Unit)
    {
        val textViewName : TextView = view.findViewById(R.id.textViewName)
        val textViewEmail: TextView = view.findViewById(R.id.textViewEmail)
        textViewName.setText(student.name)
        textViewEmail.setText(student.email)

        view.setOnClickListener{
            clickListener(student)
        }

    }
}