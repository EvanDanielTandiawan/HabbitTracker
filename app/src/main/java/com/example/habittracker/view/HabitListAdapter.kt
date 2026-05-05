package com.example.habittracker.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.databinding.ItemHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.ListViewModel

class HabitListAdapter(
    val habitList: ArrayList<Habit>,
    val viewModel: ListViewModel
) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]

        holder.binding.txtName.text = habit.name
        holder.binding.txtDesc.text = habit.description

        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.progress

        if (habit.progress >= habit.goal) {
            holder.binding.txtStatus.text = "Completed"
            holder.binding.btnPlus.isEnabled = false
        } else {
            holder.binding.txtStatus.text = "In Progress"
            holder.binding.btnPlus.isEnabled = true
        }

        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.goal) {
                habit.progress += 1
                viewModel.updateHabit(position, habit)
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            if (habit.progress > 0) {
                habit.progress -= 1
                viewModel.updateHabit(position, habit)
            }
        }
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    fun updateHabitList(newList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newList)
        notifyDataSetChanged()
    }

    class HabitViewHolder(val binding: ItemHabitBinding)
        : RecyclerView.ViewHolder(binding.root)
}