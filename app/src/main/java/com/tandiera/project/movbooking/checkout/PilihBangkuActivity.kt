package com.tandiera.project.movbooking.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.databinding.ActivityPilihBangkuBinding
import com.tandiera.project.movbooking.databinding.ActivitySignInBinding
import com.tandiera.project.movbooking.model.Checkout
import com.tandiera.project.movbooking.model.Film

class PilihBangkuActivity : AppCompatActivity() {

    // deklarasi binding
    private lateinit var binding : ActivityPilihBangkuBinding

    var statusA3 : Boolean = false
    var statusA4 : Boolean = false
    var total : Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* inflate(inflater) – Use this in an Activity onCreate where
        there is no parent view to pass to the binding object. */
        binding = ActivityPilihBangkuBinding.inflate(layoutInflater)
        /* pass the root element to tell the activity
        to use the layout form binding object (which's signUpphoto) */
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Film>("data")
        binding.tvKursi.text = data?.judul

        binding.a3.setOnClickListener {
            if (statusA3) {
                binding.a3.setImageResource(R.drawable.ic_rectangle_empty)
                statusA3 = false
                total -= 1
                beliTiket()
            } else {
                binding.a3.setImageResource(R.drawable.ic_rectangle_selected)
                statusA3 = false
                total -= 1
                beliTiket()

                val data = Checkout("A3", "70000")
                dataList.add(data)
            }
        }

        binding.a4.setOnClickListener {
            if (statusA4) {
                binding.a4.setImageResource(R.drawable.ic_rectangle_empty)
                statusA4 = false
                total -= 1
                beliTiket()
            } else {
                binding.a4.setImageResource(R.drawable.ic_rectangle_selected)
                statusA4 = false
                total -= 1
                beliTiket()

                val data = Checkout("A4", "70000")
                dataList.add(data)
            }
        }
    }

    private fun beliTiket() {
        if(total == 0) {
            binding.btnHome.setText("Beli Tiket")
            binding.btnHome.visibility = View.INVISIBLE
        } else {
            binding.btnHome.setText("Beli Tiket ("+total+")")
            binding.btnHome.visibility = View.VISIBLE
        }
    }
}