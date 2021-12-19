package com.tandiera.project.movbooking.sign.signIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.parcel.Parcelize
import android.content.Intent
import android.view.View.inflate
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
import com.tandiera.project.movbooking.databinding.ActivityCheckoutBinding.inflate
import com.tandiera.project.movbooking.databinding.ActivityCheckoutSuccessBinding.inflate
import com.tandiera.project.movbooking.onBoarding.OnBoardingTwoActivity

class SignInActivity : AppCompatActivity() {

//   Initialize Binding
    private lateinit var binding : SignInActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

////        val button1: Button = findViewById(R.id.btn_blue)
//        btn_blue.setOnClickListener {
//
//        }

        binding.name.text ="tes"
        binding.button.setOnClickListener {

        }
    }
}