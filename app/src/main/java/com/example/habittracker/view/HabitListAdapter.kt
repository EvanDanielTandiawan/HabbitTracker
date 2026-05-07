package com.example.habittracker.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
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

        val resId = holder.itemView.context.resources.getIdentifier(
            habit.icon,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.binding.imgIcon.setImageResource(resId)

        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.progress

        if (habit.progress >= habit.goal) {
            holder.binding.txtStatus.text = "Completed"
            holder.binding.btnPlus.isEnabled = false

            val Green = ContextCompat.getColor(holder.itemView.context, R.color.green)
            val colorWhite = ContextCompat.getColor(holder.itemView.context, R.color.white)

            holder.binding.txtStatus.backgroundTintList = ColorStateList.valueOf(Green)
            holder.binding.txtStatus.setTextColor(colorWhite)
            holder.binding.progressBar.progressTintList = ColorStateList.valueOf(Green)
        } else {
            holder.binding.txtStatus.text = "In Progress"
            holder.binding.btnPlus.isEnabled = true

            val Gray = ContextCompat.getColor(holder.itemView.context, R.color.gray)
            val Black = ContextCompat.getColor(holder.itemView.context, R.color.black)
            val Purple = ContextCompat.getColor(holder.itemView.context, R.color.purple)

            holder.binding.txtStatus.backgroundTintList = ColorStateList.valueOf(Gray)
            holder.binding.txtStatus.setTextColor(Black)
            holder.binding.progressBar.progressTintList = ColorStateList.valueOf(Purple)

        }
        holder.binding.txtValue.text = "${habit.progress} / ${habit.goal} ${habit.unit}"

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