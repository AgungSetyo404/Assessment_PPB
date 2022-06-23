package org.d3if2082.task_projecta

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.d3if2082.task_projecta.util.Timer

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID = "updater"
    }
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name        = getString(R.string.channel_name)
            val importance  = NotificationManager.IMPORTANCE_DEFAULT
            val channel     = NotificationChannel(CHANNEL_ID, name, importance)
                channel.description = getString(R.string.channel_desc)

            val manager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
            manager.createNotificationChannel(channel)
        }

        myTimer = Timer()

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}