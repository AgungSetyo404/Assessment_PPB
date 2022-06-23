package org.d3if2082.task_projecta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.d3if2082.task_projecta.util.Timer

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var myTimer: Timer

//    override fun onStart() {
//        super.onStart()
//        Log.i("Activity-Background", "STARTED")
//        myTimer.resetTimer()
//    }

    override fun onResume() {
        super.onResume()
        myTimer.startTimer()
        Log.i("Activity-Background", "RESUMED")
    }

    override fun onStop() {
        Log.i("Activity-Background", "STOPPED")
        myTimer.stopTimer()
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("Activity-Background", "APP DESTROYED")
        myTimer.resetTimer()
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myTimer = Timer()

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}