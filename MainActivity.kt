package com.example.bitfit



import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.FragmentTransaction
import com.example.bitfit.UserInputActivity
import com.example.bitfit.fragment.LogFragment
import com.example.bitfit.fragment.SummaryFragment

import com.humcomp.bitfitpart2.R

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav:BottomNavigationView
    private val channelName = "com.humcomp.bitfit"
    private val channelId = "com.humcomp.bitfit"
    private val description = "Notification!"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addFoodBtn :Button = findViewById(R.id.add_food_btn)

        addFoodBtn.setOnClickListener{
            val intent = Intent(this, UserInputActivity::class.java)
            startActivity(intent)
        }



        loadFragment(LogFragment())
        bottomNav = findViewById<View>(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.log -> {
                    loadFragment(LogFragment())
                    true
                }
                R.id.summary -> {
                    loadFragment(SummaryFragment())
                    true
                }
                else -> false
            }
        }

        createNotificationChannel()
        var builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Enter thr Food!")
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)


        var notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notifyThread = Thread (Runnable {
            while (true) {
                notificationManager.notify(1234, builder.build())
                Thread.sleep(86400 * 24)
            }
        })
        notifyThread.start()

    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}