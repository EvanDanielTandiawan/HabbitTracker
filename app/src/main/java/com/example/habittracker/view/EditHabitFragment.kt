package com.example.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentAddHabitBinding
import com.example.habittracker.databinding.FragmentEditHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.ListViewModel


class EditHabitFragment : Fragment() {

    private lateinit var binding: FragmentEditHabitBinding
    private val viewModel: ListViewModel by activityViewModels()

    private lateinit var habit: Habit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serializableHabit = arguments?.getSerializable("habit") as? Habit

        if (serializableHabit != null) {
            habit = serializableHabit

            binding.habit = habit
        }

        val iconList = listOf("hydration", "books", "muscle", "mastery")

        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1, iconList
        )

        binding.listIcon.setAdapter(adapter)

        binding.btnEditHabit.setOnClickListener {
            onClick(it)
        }
    }

    fun onClick(v: View) {

        habit.name =
            binding.txtInputHabitName.text.toString()

        habit.description =
            binding.txtInputDescription.text.toString()

        habit.goal =
            binding.txtInputGoal.text.toString().toIntOrNull() ?: 0

        habit.unit =
            binding.txtInputUnit.text.toString()

        habit.icon =
            binding.listIcon.text.toString()

        viewModel.updateHabit(habit.uuid, habit)

        findNavController().navigateUp()
    }

}