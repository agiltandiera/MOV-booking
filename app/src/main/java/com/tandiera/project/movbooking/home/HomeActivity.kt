package com.tandiera.project.movbooking.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.tandiera.project.movbooking.R
import com.tandiera.project.movbooking.databinding.ActivityHomeBinding
import com.tandiera.project.movbooking.databinding.ActivitySignInBinding
import com.tandiera.project.movbooking.home.dashboard.DashboardFragment

class HomeActivity : AppCompatActivity() {

    // deklarasi binding
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentHome = DashboardFragment()
        setFragment(fragmentHome)

        // Jika ivMenu1 diklik
        binding.ivMenu1.setOnClickListener{
            setFragment(fragmentHome)

            changeIcon(binding.ivMenu1, R.drawable.ic_home_active)
            changeIcon(binding.ivMenu2, R.drawable.ic_tiket)
            changeIcon(binding.ivMenu3, R.drawable.ic_profile)
        }

        binding.ivMenu2.setOnClickListener{
            setFragment(fragmentHome)

            changeIcon(binding.ivMenu1, R.drawable.ic_home)
            changeIcon(binding.ivMenu2, R.drawable.ic_tiket_active)
            changeIcon(binding.ivMenu3, R.drawable.ic_profile)
        }

        binding.ivMenu3.setOnClickListener{
            setFragment(fragmentHome)

            changeIcon(binding.ivMenu1, R.drawable.ic_home)
            changeIcon(binding.ivMenu2, R.drawable.ic_tiket)
            changeIcon(binding.ivMenu3, R.drawable.ic_profile_active)
        }
    }

    // melakukan root fragment
    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    // Function untuk merubah icon
    private fun changeIcon(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }
}