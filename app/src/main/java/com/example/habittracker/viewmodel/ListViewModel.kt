package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buildDb
import com.example.habittracker.database.HabitTrackerDatabase
import com.example.habittracker.model.Habit
import com.example.habittracker.util.FileHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val currentList = MutableLiveData<MutableList<Habit>>(mutableListOf())
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val loadingLD = MutableLiveData<Boolean>()
    val habitList: LiveData<MutableList<Habit>> = habitsLD as LiveData<MutableList<Habit>>

    init {
        launch {
            val db = buildDb(getApplication())
            val list = db.habitDao().getAllHabits()
            habitsLD.postValue(ArrayList(list))
        }
    }

    fun updateHabit(index: Int, habit: Habit) {
        val currentList = habitsLD.value ?: return

        currentList[index] = habit

        habitsLD.value = currentList

        launch {
            val db = HabitTrackerDatabase.buildDatabase(getApplication())
            db.habitDao().updateHabit(habit)
        }
    }

    fun addHabit(habit: Habit) {
        val currentList = habitsLD.value ?: arrayListOf()
        currentList.add(habit)
        habitsLD.value = currentList

        launch {
            val db = HabitTrackerDatabase.buildDatabase(getApplication())
            db.habitDao().insertHabit(habit)
        }
    }
}