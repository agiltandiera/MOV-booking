package com.tandiera.project.movbooking.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.sign.signIn.SignInActivity
import com.tandiera.project.movbooking.utils.Preferences

class OnBoardingOneActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)

        preferences = Preferences(this)

        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()

            val intent = Intent(this@OnBoardingOneActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }

        val button1: Button = findViewById(R.id.btn_pink)
        button1.setOnClickListener {
            val intent = Intent(this@OnBoardingOneActivity, OnBoardingTwoActivity::class.java)
            startActivity(intent)
        }

        val button2: Button = findViewById(R.id.btn_blue)
        button2.setOnClickListener {
            preferences.setValues("onboarding", "1")
            finishAffinity() //hapus semua page yang sudah ditampilkan

            val intent = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}