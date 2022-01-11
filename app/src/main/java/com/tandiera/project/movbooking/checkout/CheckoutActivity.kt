package com.tandiera.project.movbooking.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.databinding.ActivityCheckoutBinding
import com.tandiera.project.movbooking.databinding.ActivityPilihBangkuBinding
import com.tandiera.project.movbooking.home.dashboard.CheckoutAdapter
import com.tandiera.project.movbooking.model.Checkout
import com.tandiera.project.movbooking.utils.Preferences

class CheckoutActivity : AppCompatActivity() {

    // deklarasi binding
    private lateinit var binding : ActivityCheckoutBinding

    private var dataList = ArrayList<Checkout>()
    private var total : Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* inflate(inflater) – Use this in an Activity onCreate where
        there is no parent view to pass to the binding object. */
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        /* pass the root element to tell the activity
        to use the layout form binding object (which's signUpphoto) */
        setContentView(binding.root)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for (a in dataList.indices) {
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total harus dibyar", total.toString()))

        binding.rvCheckout.layoutManager = LinearLayoutManager(this)
        binding.rvCheckout.adapter = CheckoutAdapter(dataList) {}

        binding.btnTiket.setOnClickListener {
            var intent = Intent(this, CheckoutSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}