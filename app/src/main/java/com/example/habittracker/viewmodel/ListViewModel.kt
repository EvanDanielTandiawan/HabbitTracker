package com.example.habittracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habittracker.model.Habit

class ListViewModel: ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val loadingLD = MutableLiveData<Boolean>()
    val habitList: LiveData<MutableList<Habit>> = habitsLD as LiveData<MutableList<Habit>>

    fun addHabit(habit: Habit) {
        val currentList = habitsLD.value ?: mutableListOf()
        currentList.add(habit)
        habitsLD.value = currentList as ArrayList<Habit>?
    }
}
