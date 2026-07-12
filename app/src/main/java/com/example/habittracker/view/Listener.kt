package com.example.habittracker.view

import android.view.View
import com.example.habittracker.model.Habit

interface EditHabitListener {
    fun onClick(v: View)
}

interface ItemHabitListener {
    fun onPlusClick(v: View, habit: Habit)
    fun onMinusClick(v: View, habit: Habit)
}