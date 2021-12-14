package com.tandiera.project.movbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.tandiera.project.movbooking.onBoarding.OnBoardingTwoActivity
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase




class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val button1: Button = findViewById(R.id.btn_pink)
        button1.setOnClickListener {
            // Write a message to the database
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")

            myRef.setValue("Hello, World!")
        }
    }
}