package com.tandiera.project.movbooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.tandiera.project.movbooking.databinding.ActivityDetailBinding
import com.tandiera.project.movbooking.databinding.ActivitySignInBinding
import com.tandiera.project.movbooking.home.dashboard.PlaysAdapter
import com.tandiera.project.movbooking.model.Film
import com.tandiera.project.movbooking.model.Plays
import java.util.ArrayList

class DetailActivity : AppCompatActivity() {

    // deklarasi binding
    private lateinit var binding : ActivityDetailBinding

    // inisialisasi
    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* inflate(inflater) – Use this in an Activity onCreate where
        there is no parent view to pass to the binding object. */
        binding = ActivityDetailBinding.inflate(layoutInflater)
        /* pass the root element to tell the activity
        to use the layout form binding object (which's signUpphoto) */
        setContentView(binding.root)

        //ambil data dari dashboardFragment
        val data = intent.getParcelableExtra<Film>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data?.judul.toString())
            .child("play")

        binding.tvKursi.text = data?.judul
        binding.tvGenre.text = data?.genre
        binding.tvDesc.text = data?.desc
        binding.tvRate.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(binding.ivPoster)

        binding.rvWhoPlay.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for (getdataSnapshot in snapshot.children) {
                    var Film = getdataSnapshot.getValue(Plays::class.java)
                    dataList.add(Film!!)
                }

                binding.rvWhoPlay.adapter = PlaysAdapter(dataList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@DetailActivity, "" + error.message,
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }
}