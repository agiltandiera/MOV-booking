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
import android.Manifest
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class SignUpPhotoActivity : AppCompatActivity(), PermissionListener {

    // inisialisasi binding
    lateinit var binding: ActivitySignUpPhotoBinding

    // inisialisasi pencarian foto
    val REQUEST_IMAGE_CAPTURE = 1

    // inisialisi status ketika menambahkan foto
    // ketika menambahkan foto, maka statusadd bernilai true
    var statusAdd: Boolean = false

    // Jika mencari sebuah data di foto
    lateinit var filePath: Uri

    lateinit var storage: FirebaseStorage
    lateinit var storageRerefensi: StorageReference
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* inflate(inflater) – Use this in an Activity onCreate where
        there is no parent view to pass to the binding object. */
        binding = ActivitySignUpPhotoBinding.inflate(layoutInflater)
        /* pass the root element to tell the activity
        to use the layout form binding object (which's signUpphoto) */
        setContentView(binding.root)

        preference = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageRerefensi = storage.getReference()

        // menampilkan teks dan nama sesuai inputan user
        binding.tvHello.text = "Selamat Datang\n" + intent.getStringArrayExtra("Nama")

        binding.btnUpload.setOnClickListener {
            // status add photo
            if (statusAdd) {
                statusAdd = false
                // jika status add false, maka munculkan btn set
                binding.btnPink.visibility = View.VISIBLE
                // bentuk icon add diubah menjadi delete ketika sudah ada foto
                binding.btnUpload.setImageResource(R.drawable.ic_btn_upload)
                // default user picture
                binding.ivProfile.setImageResource(R.drawable.user_pic)
            } else {
                Dexter.withActivity(this)
                        // meminta perizinan user untuk mengakses kamera
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(this)
                    .check()

            }
        }
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        TODO("Not yet implemented")
    }
}