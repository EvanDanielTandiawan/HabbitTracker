package com.example.habittracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.habittracker.model.Habit

@Dao
interface HabitDAO {

    @Query("SELECT * FROM Habit")
    fun getAllHabits(): List<Habit>
    @Query("SELECT * FROM Habit WHERE uuid = :uuid ")
    fun getHabitById(uuid: Int): Habit?

    @Insert
    fun insertHabit(habit: Habit)

    @Update
    fun updateHabit(habit: Habit)

    @Delete
    fun deleteHabit(habit: Habit)
}