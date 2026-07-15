package com.example.habittracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittracker.dao.HabitDAO
import com.example.habittracker.dao.LogiNDAO
import com.example.habittracker.model.Habit
import com.example.habittracker.model.Login


@Database(entities = arrayOf(Login::class, Habit::class), version =  1)
abstract class HabitTrackerDatabase: RoomDatabase() {
    abstract fun loginDao(): LogiNDAO
    abstract fun habitDao(): HabitDAO
    companion object {
        @Volatile private var instance: HabitTrackerDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "habittrakerdb"

        fun buildDatabase(context: Context): HabitTrackerDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                HabitTrackerDatabase::class.java,
                DB_NAME).build()
        }

        operator fun invoke(context: Context): HabitTrackerDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }
    }
}
