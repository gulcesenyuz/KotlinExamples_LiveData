package com.example.livedatakotlin

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel(){
    private lateinit var timer:CountDownTimer
    var finished= MutableLiveData<Boolean>()
    var timerValue=MutableLiveData<Long>()
    private val _second=MutableLiveData<Int>()
    fun second():LiveData<Int>{
        return _second
    }

    fun startTimer(){
        timer=object :CountDownTimer(timerValue.value!!.toLong(),1000){
            override fun onTick(p0: Long) {
                val timeLeft=p0/1000
                _second.value=timeLeft.toInt()
            }

            override fun onFinish() {
                finished.value=true
            }

        }.start()
    }

    fun stopTimer(){
        timer.cancel()
    }
}