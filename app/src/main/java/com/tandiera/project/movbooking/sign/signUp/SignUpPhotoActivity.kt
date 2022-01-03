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
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.tandiera.project.movbooking.home.HomeActivity
import java.util.*

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

//    lateinit var user : User
//    private lateinit var mFirebaseDatabase: DatabaseReference
//    private lateinit var mFirebaseInstance: FirebaseDatabase

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

        binding.btnBlue.setOnClickListener {
            // foto bersifat opsional
            finishAffinity()

            var goHome = Intent(this@SignUpPhotoActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        // btn untuk save
        binding.btnPink.setOnClickListener {
            // buat kondisi untuk upload ke firebase
            if(filePath != null) {
                // masuk ke progress upload
                    // memberitahu user jika sedang mengupload foto
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                var ref = storageRerefensi.child("image/"+ UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Uploaded", Toast.LENGTH_LONG).show()

                        ref.downloadUrl.addOnSuccessListener {
                            preference.setValues("url", it.toString())
                        }

                        finishAffinity()
                        var goHome = Intent(this@SignUpPhotoActivity, HomeActivity::class.java)
                        startActivity(goHome)
                    }
                    .addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener {
                        taskSnapshot -> var progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Upload " + progress.toInt()+"%")
                    }

            } // jika file null
            else {

            }
        }
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this, "Anda tidak bisa menambahkan foto profil", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Tergesah? Klik tombol upload nanti", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            var bitmap = data?.extras?.get("data") as Bitmap
            statusAdd = true

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivProfile)

            binding.btnPink.visibility = View.VISIBLE
            binding.ivProfile.setImageResource(R.drawable.ic_btn_delete)
        }
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
    }
}