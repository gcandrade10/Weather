package com.example.weathersample

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

class IntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSlide(R.string.intro_welcome_title, R.string.intro_welcome_description)
        addSlide(R.string.intro_search_title, R.string.intro_search_description)
        addSlide(R.string.intro_forecasts_title, R.string.intro_forecasts_description)
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }

    private fun addSlide(title: Int, description: Int) {
        addSlide(AppIntroFragment.newInstance(
                title = getString(title),
                description = getString(description),
                backgroundColor = Color.GRAY
        ))
    }

}