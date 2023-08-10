package com.myshopapp.testapp.ui

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.media.RingtoneManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.myshopapp.testapp.MyApplication
import com.myshopapp.testapp.R
import com.myshopapp.testapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        job = lifecycleScope.launch {
            delay(2000)
            startHome()
        }
    }

    private fun showImageNotification(
        title: String,
        message: String,
        imageUrl: Bitmap?,
        iconUrl: Bitmap?,
        intent: Intent?
    ) {
        val pendingIntent = PendingIntent.getActivity(
            this, 1, intent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_ONE_SHOT
        )
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        NotificationManagerCompat.from(this)
            .notify(
                1,
                NotificationCompat.Builder(this, " NotificationChannel.CHANNEL_IN_APP")
                    .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                    .setContentTitle(title)
                    .setContentText(message)
                    .setLargeIcon(null)
                    .setStyle(
                        NotificationCompat.BigPictureStyle()
                            .bigPicture(imageUrl)
                            .bigLargeIcon(iconUrl)
                    )
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent).build()
            )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        job?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun startHome() {
        if (MyApplication.instance?.userStorage?.isUserLoggedIn() == true)
            startActivity(Intent(this, MainActivity::class.java))
        else
            startActivity(Intent(this, LoginActivity::class.java))
    }
}