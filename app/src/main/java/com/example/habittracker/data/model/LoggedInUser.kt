package com.example.habittracker.data.model // Pastikan package ini sesuai dengan lokasi folder aslinya

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String
)