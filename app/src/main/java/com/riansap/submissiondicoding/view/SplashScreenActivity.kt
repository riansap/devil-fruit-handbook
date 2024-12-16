package com.riansap.submissiondicoding.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.riansap.submissiondicoding.databinding.ActivitySplashScreenBinding

/**
 * SplashScreenActivity
 *
 * Aktivitas ini bertugas untuk menampilkan layar splash saat pertama kali aplikasi dibuka.
 * Layar ini biasanya digunakan untuk menunjukkan logo aplikasi dan memberikan waktu untuk memuat komponen penting.
 */
class SplashScreenActivity : AppCompatActivity() {

    // Menghubungkan dengan layout menggunakan View Binding
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengikat layout menggunakan View Binding
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Panggil fungsi untuk berpindah ke halaman utama
        navigateToMainActivity()
    }

    /**
     * Fungsi untuk berpindah ke MainActivity setelah menunggu selama 2,5 detik.
     * Ini memberikan pengalaman yang lebih mulus kepada pengguna.
     */
    private fun navigateToMainActivity() {
        // Tunggu selama 2,5 detik sebelum berpindah ke MainActivity
        window.decorView.postDelayed({
            val intent = Intent(this, MainActivity::class.java) // Buat intent untuk berpindah
            startActivity(intent) // Jalankan intent
            finish() // Hapus SplashScreenActivity dari stack aktivitas
        }, 2500) // Tunggu selama 2500 milidetik (2,5 detik)
    }
}
