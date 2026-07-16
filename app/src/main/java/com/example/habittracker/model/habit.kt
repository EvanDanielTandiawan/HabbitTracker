package com.example.habittracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Habit")
data class Habit(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "goal")
    var goal: Int,

    @ColumnInfo(name = "progress")
    var progress: Int,

    @ColumnInfo(name = "unit")
    var unit: String,

    @ColumnInfo(name = "icon")
    var icon: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

    fun getStatus(): String {
        return if (progress >= goal) {
            "Completed"
        } else {
            "In Progress"
        }
    }
}