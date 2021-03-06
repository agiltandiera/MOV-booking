package com.tandiera.project.movbooking.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.databinding.ActivitySignInBinding
import com.tandiera.project.movbooking.databinding.ActivityTiketBinding
import com.tandiera.project.movbooking.model.Checkout
import com.tandiera.project.movbooking.model.Film

class TiketActivity : AppCompatActivity() {

    // deklarasi binding
    private lateinit var binding : ActivityTiketBinding

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* inflate(inflater) – Use this in an Activity onCreate where
        there is no parent view to pass to the binding object. */
        binding = ActivityTiketBinding.inflate(layoutInflater)
        /* pass the root element to tell the activity
        to use the layout form binding object (which's signUpphoto) */
        setContentView(binding.root)

        var data = intent.getParcelableExtra<Film>("data")

        binding.tvTitle.text = data?.judul
        binding.tvGenre.text = data?.genre
        binding.tvRate.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(binding.ivPosterImage)

        binding.rvCheckout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1", ""))
        dataList.add(Checkout("C2", ""))

        binding.rvCheckout.adapter = TiketAdapter(dataList) {

        }

        binding.ivArrowBack.setOnClickListener {
            finish()
        }

        binding.ivBarcode.setOnClickListener {
            showDialog("Silahkan melakukan scanning pada counter tiket terdekat")
        }
    }

    private fun showDialog(title: String) {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.dialog_qr)
//        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
//        val tvDesc = dialog.findViewById(R.id.tv_desc) as TextView
//        tvDesc.text = title
//
//        val btnClose = dialog.findViewById(R.id.btn_close) as Button
//        btnClose.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.show()
    }
}