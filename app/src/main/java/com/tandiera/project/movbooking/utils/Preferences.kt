package com.tandiera.project.movbooking.utils

import android.content.Context
import android.content.SharedPreferences

// INI DATABASE SEMENTARA
// AGAR KETIKA LOGIN, MAKA BAGIAN ONBOARDING DAN DATA BISA MENYESUAIKAN
// JADI KETIKA SUDAH LOGIN, MAKA USER TDK PERLU SIGN UP BERKALI-KALI
class Preferences (context: Context) {
    // deklarasi objek
    companion object {
        const val USER_PREFF = "USER_PREFF"
    }

    // sharedPreferences : class sebagai tempat penyimpanan data primitif pada internal aplikasi
    // berbentu key-value
    /* getSharedPreferences : digunakan jika memerlukan beberapa file preferensi
    bersama yang diidentifikasi menurut nama */
    var sharedPreferences = context.getSharedPreferences(USER_PREFF,0)

    // SET VALUE
    fun setValues(key: String, value: String) {
        // melakukan perizinan : apakah boleh mengedit atau menambahkan data
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        // putString : to pass the keys and values
        editor.putString(key, value)
        // apply() : to save the changes
        editor.apply()
    }

    // AMBIL VALUE
    fun getValues(key: String) : String? {
        return sharedPreferences.getString(key, "")
    }
}