package com.tandiera.project.movbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.tandiera.project.movbooking.onBoarding.OnBoardingTwoActivity
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase


class SignInActivity : AppCompatActivity() {

    lateinit var  iUsername: String
    lateinit var  iPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val button1: Button = findViewById(R.id.btn_pink)
        button1.setOnClickListener {
            iUsername: EditText = findViewById(R.id.editTxtPass)
            iUsername = editTxtPass.text.toString()
        }

        val edit: EditText
    }
}