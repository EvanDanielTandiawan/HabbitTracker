package com.example.habittracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.habittracker.model.Login

@Dao
interface LogiNDAO {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertUser(login: Login)

        @Query("SELECT * FROM Login WHERE username = :username AND password = :password")
        fun login(username: String, password: String): Login?

        @Query("SELECT * FROM Login WHERE username = :username ")
        fun checkUsername(username: String): Login?

        @Delete
        fun deleteUser(user: Login)

        @Update
        fun updateUser(user: Login)

}
