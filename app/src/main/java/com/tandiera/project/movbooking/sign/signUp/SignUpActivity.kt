package com.tandiera.project.movbooking.sign.signUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tandiera.project.movbooking.R
import java.util.prefs.Preferences

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//        mFirebaseInstance = FirebaseDatabase.getInstance()
//        mDatabase = FirebaseDatabase.getInstance().getReference()
//        mFirebaseDatabase = mFirebaseInstance.getReference("User")
//
//        preferences = Preferences(this)
//
//        val button1: Button = findViewById(R.id.btn_pink)
//        button1.setOnClickListener {
//            sUsername = et_username.text.toString()
//            sPassword = et_password.text.toString()
//            sNama = et_nama.text.toString()
//            sEmail = et_email.text.toString()
//
//            if (sUsername.equals("")) {
//                et_username.error = "Silahkan isi Username"
//                et_username.requestFocus()
//            } else if (sPassword.equals("")) {
//                et_password.error = "Silahkan isi Password"
//                et_password.requestFocus()
//            } else if (sNama.equals("")) {
//                et_nama.error = "Silahkan isi Nama"
//                et_nama.requestFocus()
//            } else if (sEmail.equals("")) {
//                et_email.error = "Silahkan isi Email"
//                et_email.requestFocus()
//            } else {
//
//                var statusUsername = sUsername.indexOf(".")
//                if (statusUsername >=0) {
//                    et_username.error = "Silahkan tulis Username Anda tanpa ."
//                    et_username.requestFocus()
//                } else {
//                    saveUser(sUsername, sPassword, sNama, sEmail)
//                }
//
//            }
//        }
    }
}