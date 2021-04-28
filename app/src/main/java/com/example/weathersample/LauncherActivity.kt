package com.example.weathersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        val prefs = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE)
        val isIntroCompleted = prefs.getBoolean(INTRO_COMPLETED_KEY, false)
        if (!isIntroCompleted) {
            startActivity(IntroActivity.newIntent(this))
        }
    }

    companion object {
        const val SHARED_PREFERENCE = "SHARED PREFERENCE"
        const val INTRO_COMPLETED_KEY = "INTRO COMPLETED"
    }
}