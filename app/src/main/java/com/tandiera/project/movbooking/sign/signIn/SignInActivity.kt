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
import com.tandiera.project.movbooking.databinding.ActivityCheckoutBinding.bind
import com.tandiera.project.movbooking.databinding.ActivityCheckoutBinding.inflate
import com.tandiera.project.movbooking.databinding.ActivityCheckoutSuccessBinding.inflate
import com.tandiera.project.movbooking.databinding.ActivitySignInBinding
import com.tandiera.project.movbooking.onBoarding.OnBoardingTwoActivity

class SignInActivity : AppCompatActivity() {

//   Initialize Binding
    private lateinit var binding : ActivitySignInBinding
    lateinit var iUsername: String
    lateinit var iPassword : String

    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        isi value database
        mDatabase = FirebaseDatabase.getInstance().getReference("User")

        binding.btnPink.setOnClickListener {
            iUsername = binding.etUsername.text.toString()
            iPassword = binding.etPassword.text.toString()

            if(iUsername.equals("")) {
                binding.etUsername.error = "Silakan masukkan username Anda"
                binding.etUsername.requestFocus()
            } else if(iPassword.equals("")) {
                binding.etPassword.error = "Silakan masukkan password Anda"
                binding.etPassword.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }

        binding.btnBlue.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@SignInActivity, error.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)

                if (user == null) { //jika data null, maka tampilkan di bawah ini
                    Toast.makeText(
                        this@SignInActivity, "User tidak ditemukan",
                        Toast.LENGTH_LONG
                    ).show()
                } else { //jika data ditemukn, maka ke home page

                    // cek apakah password sama dgn database
                    if (user.password.equals(iPassword)) {
                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@SignInActivity, "Password Anda Salah",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }
}