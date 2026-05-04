package com.example.habittracker.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.databinding.ItemHabitBinding
import com.example.habittracker.model.Habit

class HabitListAdapter(val habitList:ArrayList<Habit>)
    : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int
    ) {
        holder.binding.txtName.text = habitList[position].name
        holder.binding.txtDesc.text = habitList[position].description

        holder.binding.progressBar.max = habitList[position].goal
        holder.binding.progressBar.progress = habitList[position].progress

        if (habitList[position].progress >= habitList[position].goal){
            holder.binding.txtStatus.text = "Completed"
            holder.binding.btnPlus.isEnabled = false
        } else{
            holder.binding.txtStatus.text = "In Progress"
            holder.binding.btnPlus.isEnabled = true
        }

        holder.binding.btnPlus.setOnClickListener {
            if (habitList[position].progress < habitList[position].goal) {
                habitList[position].progress += 1;
            }

        }
        holder.binding.btnMinus.setOnClickListener {
            if (habitList[position].progress > 0) {
                habitList[position].progress -= 1;
            }

        }


    }

    override fun getItemCount(): Int {
        return habitList.size
    }
    fun updateHabitList(newHabitListAdapter:ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitListAdapter)
        notifyDataSetChanged()
    }

    class HabitViewHolder(var binding: ItemHabitBinding)
        :RecyclerView.ViewHolder(binding.root)


}
