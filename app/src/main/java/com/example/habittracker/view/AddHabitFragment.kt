package com.example.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentAddHabitBinding
import com.example.habittracker.databinding.FragmentDashboardBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.ListViewModel

class AddHabitFragment : Fragment() {

    private lateinit var binding: FragmentAddHabitBinding
    private val habitViewModel: ListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddHabitBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val iconList = listOf("hydration", "books", "muscle", "mastery")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, iconList)
        binding.listIcon.setAdapter(adapter)

        binding.btnCreateHabit.setOnClickListener {
            val name = binding.txtInputHabitName.text.toString()
            val desc = binding.txtInputDescription.text.toString()
            val goal = binding.txtInputGoal.text.toString()
            val unit = binding.txtInputUnit.text.toString()
            val iconName = binding.listIcon.text.toString()

            val newHabit = Habit(
                name = name,
                description = desc,
                progress = 0,
                goal = goal.toInt(),
                unit = unit,
                icon = iconName
            )

            habitViewModel.addHabit(newHabit)
            findNavController().popBackStack()
        }
    }

}