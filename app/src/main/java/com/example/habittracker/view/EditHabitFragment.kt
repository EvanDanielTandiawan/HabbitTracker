package com.example.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentAddHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.ListViewModel


class EditHabitFragment : Fragment() {

    private lateinit var binding: FragmentAddHabitBinding
    private lateinit var viewModel: ListViewModel

    private lateinit var habit: Habit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        val serializableHabit = arguments?.getSerializable("habit") as? Habit

        if (serializableHabit != null) {
            habit = serializableHabit

            binding.txtInputHabitName.setText(habit.name)
            binding.txtInputDescription.setText(habit.description)
            binding.txtInputGoal.setText(habit.goal.toString())
            binding.txtInputUnit.setText(habit.unit)
            binding.listIcon.setText(habit.icon, false)

            binding.btnCreateHabit.text = "Update Habit"
        }

        val iconList = listOf("hydration", "books", "muscle", "mastery")

        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1, iconList
        )

        binding.listIcon.setAdapter(adapter)

        binding.btnCreateHabit.setOnClickListener {
            onClick(it)
        }
    }

    fun onClick(v: View) {
        val name = binding.txtInputHabitName.text.toString()
        val desc = binding.txtInputDescription.text.toString()
        val goal = binding.txtInputGoal.text.toString().toIntOrNull() ?: 0
        val unit = binding.txtInputUnit.text.toString()
        val iconName = binding.listIcon.text.toString()

        viewModel.updateHabit(habit.uuid, habit)

        findNavController().navigateUp()
    }

}