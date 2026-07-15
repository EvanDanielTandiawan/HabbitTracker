package com.example.habittracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittracker.dao.LogiNDAO      // Memakai nama file asli kamu LogiNDAO
import com.example.habittracker.dao.HabitDAO      // Memakai nama file asli kamu HabitDAO
import com.example.habittracker.model.Login
import com.example.habittracker.model.Habit

@Database(entities = [Login::class, Habit::class], version = 1)
abstract class HabitTrackerDatabase : RoomDatabase() {

    // Menyesuaikan tipe return dengan nama interface asli kamu
    abstract fun loginDao(): LogiNDAO
    abstract fun habitDao(): HabitDAO

    companion object {
        @Volatile
        private var INSTANCE: HabitTrackerDatabase? = null

        fun getDatabase(context: Context): HabitTrackerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitTrackerDatabase::class.java,
                    "habittracker_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}