package com.example.habittracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val statusLoginLD = MutableLiveData<Boolean>()

    fun checkLogin(username:String, password: String){
        if(username=="student" && password =="123"){
            statusLoginLD.value = true
        }else{
            statusLoginLD.value = false
        }

    }
}