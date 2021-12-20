package com.tandiera.project.movbooking.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.databinding.ActivityOnBoardingOneBinding
import com.tandiera.project.movbooking.sign.signIn.SignInActivity
import com.tandiera.project.movbooking.utils.Preferences

class OnBoardingOneActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingOneBinding

    private lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preferences(this)

        if(preference.getValues("onboarding").equals("1")) {
            var goHome = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(goHome)
        }

//        BUAT LISTENER
        val button1: Button = findViewById(R.id.btn_pink)
        button1.setOnClickListener {
            val intent = Intent(this@OnBoardingOneActivity, OnBoardingTwoActivity::class.java)
            startActivity(intent)
        }

        val button2: Button = findViewById(R.id.btn_blue)
        button2.setOnClickListener {
            preference.setValues("onBoarding", "1")
            finishAffinity()

            val intent = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}