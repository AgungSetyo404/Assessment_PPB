package org.d3if2082.task_projecta.network

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.d3if2082.task_projecta.MainActivity
import org.d3if2082.task_projecta.R

class CustomWorker (context: Context,
                    workerParams:WorkerParameters
                    ): Worker(context, workerParams) {
    private val notifId = 404
    override fun doWork(): Result {
//        Log.d("Worker", "doWork Running...")
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val notifBuilder = NotificationCompat.Builder(
            applicationContext, MainActivity.CHANNEL_ID
        )

            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(applicationContext.getString(R.string.notif_title))
            .setContentText(applicationContext.getString(R.string.notif_text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(applicationContext)
            .notify(notifId, notifBuilder.build())

        return Result.success()
    }
}