package com.tandiera.project.movbooking.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.databinding.ActivityCheckoutSuccessBinding
import com.tandiera.project.movbooking.databinding.ActivityPilihBangkuBinding
import com.tandiera.project.movbooking.home.HomeActivity

class CheckoutSuccessActivity : AppCompatActivity() {

    // deklarasi binding
    private lateinit var binding : ActivityCheckoutSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* inflate(inflater) – Use this in an Activity onCreate where
        there is no parent view to pass to the binding object. */
        binding = ActivityCheckoutSuccessBinding.inflate(layoutInflater)
        /* pass the root element to tell the activity
        to use the layout form binding object (which's signUpphoto) */
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            finishAffinity()

            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}