package com.tandiera.project.movbooking.sign.signIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.google.firebase.database.*

import com.tandiera.project.movbooking.home.HomeActivity
import com.tandiera.project.movbooking.sign.signUp.SignUpActivity
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import com.tandiera.project.movbooking.databinding.ActivitySignInBinding
import com.tandiera.project.movbooking.utils.Preferences

class SignInActivity : AppCompatActivity() {

//   Initialize Binding
    private lateinit var binding : ActivitySignInBinding
    lateinit var iUsername: String
    lateinit var iPassword : String

    lateinit var mDatabase : DatabaseReference
    lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        isi value database
        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        // onBoarding hanya muncul ketika app dirun pertama kali
        // setelah kedua kali dirun, maka onBoarding tidak akan muncul lagi
        preference.setValues("onBoarding", "1")
        if(preference.getValues("status").equals("1")) {
            // Lngsung masuk ke menu home

            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }
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
                        // jika sukses, maka ambil data
                            preference.setValues("Nama", user.nama.toString())
                            preference.setValues("User", user.username.toString())
                            preference.setValues("URL", user.url.toString())
                            preference.setValues("Email", user.email.toString())
                            preference.setValues("Saldo", user.saldo.toString())
                            preference.setValues("Status", "1")

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