package org.d3if2082.task_projecta.util

import android.os.Handler
import android.os.Looper
import android.util.Log


class Timer {

    private var secondsCount = 0

    private  var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    private var reset: Boolean = false



    fun startTimer() {

        runnable = Runnable {
            secondsCount++
            var seconds = secondsCount % 60
            var minutes = secondsCount / 60
            Log.i("Timer","Time elapsed on this APP " + minutes + ":" + String.format("%02d", seconds))
            handler.postDelayed(runnable, 1000)
            if (reset == true){
                secondsCount = 0
                reset = false
            }
        }
        handler.postDelayed(runnable, 1000)
    }

    fun resetTimer() {
        reset = true
    }

    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }
}