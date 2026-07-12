package com.example.habittracker.util

import android.content.Context
import com.example.habittracker.database.HabitTrackerDatabase
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileHelper(val context: Context) {

    private val folderName = "habit_folder"
    private val fileName = "habit_data.json"




    private fun getFile(): File {
        val dir = File(context.filesDir, folderName)
        if (!dir.exists()) dir.mkdirs()
        return File(dir, fileName)
    }

    fun writeToFile(data: String) {
        try {
            val file = getFile()
            FileOutputStream(file, false).use { output ->
                output.write(data.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFromFile(): String {
        return try {
            val file = getFile()
            file.bufferedReader().useLines {
                it.joinToString("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace().toString()
        }
    }
    // Hapus file
    fun deleteFile(): Boolean {
        return getFile().delete()
    }

    // Menghasilkan string path menuju file
    fun getFilePath(): String {
        return getFile().absolutePath
    }

}