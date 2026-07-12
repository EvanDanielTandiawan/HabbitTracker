package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buildDb
import com.example.habittracker.database.HabitTrackerDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    val statusLoginLD = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun checkLogin(username:String, password: String){
        launch {
            val db = buildDb(getApplication())
            if(username=="student" && password =="123"){
                val user = db.loginDao().login(username,password)
                statusLoginLD.postValue(true)
            }else{
                statusLoginLD.postValue(false)
            }

        }



    }
}