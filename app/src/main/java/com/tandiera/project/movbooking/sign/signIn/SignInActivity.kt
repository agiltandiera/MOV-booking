package com.tandiera.project.movbooking.sign.signIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.parcel.Parcelize
import android.content.Intent
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.*

import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.home.HomeActivity
import com.tandiera.project.movbooking.sign.signUp.SignUpActivity
import com.tandiera.project.movbooking.utils.Preferences
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tandiera.project.movbooking.onBoarding.OnBoardingTwoActivity

class SignInActivity : AppCompatActivity() {

    lateinit var iUsername : String
    lateinit var iPassword : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

//        val button1: Button = findViewById(R.id.btn_blue)
        btn_blue.setOnClickListener {

        }
    }
}