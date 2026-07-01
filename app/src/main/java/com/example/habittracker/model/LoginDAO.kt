package com.example.habittracker.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.habittracker.data.model.LoggedInUser

@Dao

interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: LoggedInUser)

    @Query("SELECT * FROM Login WHERE username = :username AND password = :password LIMIT 1")
    fun login(username: String, password: String): Login?

    @Query("SELECT * FROM Login WHERE username = :username LIMIT 1")
    fun checkUsername(username: String): Login?

    @Delete
    fun deleteUser(user: Login)

    @Update
    fun updateUser(user: Login)

}