package com.tandiera.project.movbooking.sign.signUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.*
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.databinding.ActivitySignUpBinding
import com.tandiera.project.movbooking.sign.signIn.User
import java.util.prefs.Preferences

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding

    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sNama: String
    lateinit var sEmail: String

    //Initialize firebase
    lateinit var mFirebaseDatabaseReference: DatabaseReference
    lateinit var  mFirebaseInstance : FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mFirebaseDatabaseReference = mFirebaseInstance.getReference()
        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()

        // Tampung data
        binding.btnPink.setOnClickListener {

            sUsername = binding.etUsername.text.toString()
            sPassword = binding.etPassword.text.toString()
            sNama = binding.etNama.text.toString()
            sEmail = binding.etEmail.text.toString()

            // Validasi data yang dimasukkan
            if(sUsername.equals("")) {
                binding.etUsername.error = "Silakan masukkan username Anda"
                binding.etUsername.requestFocus()
            } else if(sPassword.equals("")) {
                binding.etPassword.error = "Silakan masukkan password Anda"
                binding.etPassword.requestFocus()
            } else if(sNama.equals("")) {
                binding.etNama.error = "Silakan masukkan nama Anda"
                binding.etNama.requestFocus()
            } else if(sEmail.equals("")) {
                binding.etEmail.error = "Silakan masukkan email Anda"
                binding.etEmail.requestFocus()
            } else { //jika semua data ada
                saveUsername(sUsername, sPassword, sNama, sEmail)
            }
        }
    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if(sUsername != null) {
            chekcingUsername(sUsername, user)
        }

    }

    private fun chekcingUsername(sUsername: String, data: User) {
        // terapkan firebase
        mFirebaseDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+ error.message, Toast.LENGTH_LONG)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                // CEK DATA
                var user = snapshot.getValue(User::class.java)

                if(user == null) {
                    mFirebaseDatabaseReference.child(sUsername).setValue(user)

                    var goSignUpPhoto = Intent(this@SignUpActivity, SignUpPhotoActivity::class.java).putExtra("nama", data.nama)
                    startActivity(goSignUpPhoto)
                } else {
                    Toast.makeText(this@SignUpActivity, "Username sudah digunakan", Toast.LENGTH_LONG)
                }
            }
        })
    }
}