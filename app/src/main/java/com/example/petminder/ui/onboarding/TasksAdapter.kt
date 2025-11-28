package com.example.petminder.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petminder.R

class TasksAdapter(private val tasks: List<Pair<String, String>>) :
    RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTaskTitle)
        val txtDetails: TextView = itemView.findViewById(R.id.txtTaskDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val (title, details) = tasks[position]
        holder.txtTitle.text = title
        holder.txtDetails.text = details
    }

    override fun getItemCount() = tasks.size
}
