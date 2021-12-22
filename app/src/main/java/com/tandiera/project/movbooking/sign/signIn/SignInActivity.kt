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

    // deklarasi binding
    private lateinit var binding : ActivitySignInBinding

    // deklarasi variabel untuk menampung value
    lateinit var iUsername: String
    lateinit var iPassword : String

    // deklarasi firebase untuk terkoneksi
    // untuk dapat membaca dan menulis data dari database, memerlukan instance DatabaseReference
    lateinit var mDatabase : DatabaseReference

    lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* inflate(inflater) – Use this in an Activity onCreate where
        there is no parent view to pass to the binding object. */
        binding = ActivitySignInBinding.inflate(layoutInflater)
        /* pass the root element to tell the activity
        to use the layout form binding object (which's signUpphoto) */
        setContentView(binding.root)

        // untuk dapat membaca dan menulis data dari database, memerlukan instance DatabaseReference
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

        // setOnClickListener dipanggil ketika tombol btnPink diklik
        // btnPink yaitu MASUK AKUN
        binding.btnPink.setOnClickListener {
            /* variabel iUsername untuk menampung value berupa username
            dalam bentuk string yang diinput oleh user */
            iUsername = binding.etUsername.text.toString()
            /* variabel iPassword untuk menampung value berupa password
            dalam bentuk string yang diinput oleh user */
            iPassword = binding.etPassword.text.toString()

            // Jika username kosong, maka akan muncul pesan error
            if(iUsername.equals("")) {
                binding.etUsername.error = "Silakan masukkan username Anda"
                // Jika ada error, maka kursor difokuskan ke field untuk mengisi username
                binding.etUsername.requestFocus()
            }  // Jika password kosong, maka akan muncul pesan error
            else if(iPassword.equals("")) {
                binding.etPassword.error = "Silakan masukkan password Anda"
                // Jika ada error, maka kursor difokuskan ke field untuk mengisi password
                binding.etPassword.requestFocus()
            } /* Jika semua field tidak kosong (semua terisi),
                 maka panggil function pushLogin */
            else {
                // dalam function pushLogin, terdapat parameter iUsername dan iPassword
                pushLogin(iUsername, iPassword)
            }
        }

        // setOnClickListener dipanggil ketika tombol btnBlue diklik
        binding.btnBlue.setOnClickListener {
            // ketika user klik btnBlue, maka pindah ke SignUpActivity
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


//    FUNCTION PUSHLOGIN : dipanggil ketika semua field terisi
    private fun pushLogin(iUsername: String, iPassword: String) {
    // child() : untuk membuat lokasi penyimpanan dan mengkategorikan data yg dimasukkan
    // addValueListener() : untuk menambahkan ValueEvenetListener ke DatabaseReference
    // ValueEventListener : dapat membaca dan 'listen' perubahan, agar aplikasi dapat diperbarui secara real-time
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {

            /* onDataCancelled : is called if the read is canceled
            misalnya, ketika user tidak mengizinkan read data ke databse,
            maka akan muncul pesan error.
             */
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@SignInActivity, error.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            /*
            onDataChange() : to read a static snapshot of the contents at a given path,
            as they existed at the time of the event. The onDataChange() method is called
            every time data is changed at the specified database reference, including changes to children
            */
            // DataSnapshot : contains the data at the specified location in the database at the time of the event
            override fun onDataChange(snapshot: DataSnapshot) {
                /* getValue() : returns the Java object representation of the data.
                 If no data exists at the location, calling getValue() returns null.
                 */
                var user = snapshot.getValue(User::class.java)

                //jika data null, maka tampilkan di bawah ini
                if (user == null) {
                    // Toast : pesan kecil yang tampil di layar
                        // format : Toast.makeText(context, text, duration).show()
                        // makeText() : untuk membuat object toast
                    Toast.makeText(
                        // isi toast : context, teks yang akan muncul, durasi toast
                        this@SignInActivity, "User tidak ditemukan",
                        Toast.LENGTH_LONG
                    ).show() //show() : menampilkan toast
                } //jika data ditemukan, maka ke home page
                else {
                    // cek apakah password sama dgn database
                    if (user.password.equals(iPassword)) {
                        // jika sukses, maka ambil data
                            preference.setValues("Nama", user.nama.toString())
                            preference.setValues("User", user.username.toString())
                            preference.setValues("URL", user.url.toString())
                            preference.setValues("Email", user.email.toString())
                            preference.setValues("Saldo", user.saldo.toString())
                            preference.setValues("Status", "1")

                        // jika sukses, pindah ke activity Home
                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Jika gagal, muncul pesan error
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