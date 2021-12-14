package com.tandiera.project.movbooking

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import com.tandiera.project.movbooking.onBoarding.OnBoardingOneActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity,
                OnBoardingOneActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}