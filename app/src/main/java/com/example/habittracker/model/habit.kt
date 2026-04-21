package com.example.habittracker.model

data class Habit(
    val id: Int,
    var name: String,
    var description: String,
    var goal: Int,
    var progress: Int,
    var icon: Int
)