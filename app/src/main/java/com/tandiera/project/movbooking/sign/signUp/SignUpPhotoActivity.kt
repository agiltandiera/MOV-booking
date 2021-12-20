package com.tandiera.project.movbooking.sign.signUp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.databinding.ActivitySignUpPhotoBinding
import com.tandiera.project.movbooking.utils.Preferences
import java.util.jar.Manifest

class SignUpPhotoActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpPhotoBinding

    // inisialisasi pencarian foto
    val REQUEST_IMAGE_CAPTURE = 1
    // inisialis status ketika menambahkan foto
    // ketika menambahkan foto, maka statusadd bernilai true
    var statusAdd: Boolean = false
    // Jika mencari sebuah data di foto
    lateinit var filePath : Uri

    lateinit var storage : FirebaseStorage
    lateinit var storageRerefensi : StorageReference
    lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPhotoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_sign_up_photo)

        preference = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageRerefensi = storage.getReference()

        binding.tvHello.text = "Selamat Datang\n" + intent.getStringArrayExtra("Nama")

        binding.btnUpload.setOnClickListener {
            if(statusAdd) {
                statusAdd = false
                binding.btnPink.visibility = View.VISIBLE
                binding.btnUpload.setImageResource(R.drawable.ic_btn_upload)
                binding.ivProfile.setImageResource(R.drawable.user_pic)
//            } else {
//                Dexter.withActivity(this)
//                    .withPermission(Manifest.permission.CAMERA)
//
//            }
        }

    }
}