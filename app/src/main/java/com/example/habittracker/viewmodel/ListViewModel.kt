package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.habittracker.model.Habit
import com.example.habittracker.util.FileHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    fun updateHabit(index: Int, habit: Habit) {
        val currentList = habitsLD.value ?: return

        currentList[index] = habit

        habitsLD.value = currentList

        saveToFile(currentList)
    }

    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val loadingLD = MutableLiveData<Boolean>()
    val habitList: LiveData<MutableList<Habit>> = habitsLD as LiveData<MutableList<Habit>>

    private val fileHelper = FileHelper(getApplication())
    private val gson = Gson()

    init {
        habitsLD.value = loadFromFile()
    }

    fun addHabit(habit: Habit) {
        val currentList = habitsLD.value ?: arrayListOf()
        currentList.add(habit)
        habitsLD.value = currentList

        saveToFile(currentList)
    }

    private fun saveToFile(list: List<Habit>) {
        val json = gson.toJson(list)
        fileHelper.writeToFile(json)
    }

    private fun loadFromFile(): ArrayList<Habit> {
        val json = fileHelper.readFromFile()

        if (json.isEmpty()) return arrayListOf()

        return try {
            val type = object : TypeToken<ArrayList<Habit>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}