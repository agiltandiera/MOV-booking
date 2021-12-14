package com.tandiera.project.movbooking.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.SignInActivity

class OnBoardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)

        val buttonPink: Button = findViewById(R.id.btn_pink)
        buttonPink.setOnClickListener {
            finishAffinity() //hapus semua page yang sudah ditampilkan
            val intent = Intent(this@OnBoardingThreeActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}