package com.example.habittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentDashboardBinding
import com.example.habittracker.databinding.FragmentLoginBinding
import com.example.habittracker.viewmodel.ListViewModel
import com.example.habittracker.viewmodel.LoginViewModel

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: ListViewModel by activityViewModels()
    private val habitListAdapter by lazy {
        HabitListAdapter(
            arrayListOf(),
            viewModel
        ) { habit ->

            val bundle = Bundle()
            bundle.putSerializable("habit", habit)

            findNavController().navigate(
                R.id.editHabitFragment,
                bundle
            )
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater,container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = habitListAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.habitsLD.observe(viewLifecycleOwner, Observer {
            habitListAdapter.updateHabitList(it)
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recViewHabit.visibility = View.GONE
            } else {
                binding.recViewHabit.visibility = View.VISIBLE
            }
        })

        binding.btnAddHabit.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragment2ToAddHabitFragment3()
            findNavController().navigate(action)
        }
    }

}