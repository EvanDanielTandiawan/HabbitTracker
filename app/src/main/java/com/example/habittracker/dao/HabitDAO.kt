package com.example.habittracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.habittracker.model.Habit

@Dao
interface HabitDAO {

    @Query("SELECT * FROM Habit")
    fun getAllHabits(): List<Habit>

    // KITA SAMAKAN: Mengubah nama parameter dari 'id' menjadi 'uuid'
    @Query("SELECT * FROM Habit WHERE uuid = :uuid LIMIT 1")
    fun getHabitById(uuid: Int): Habit?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(habit: Habit)

    @Update
    fun updateHabit(habit: Habit)

    @Delete
    fun deleteHabit(habit: Habit)
}