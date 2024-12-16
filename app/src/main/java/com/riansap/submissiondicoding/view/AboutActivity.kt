package com.riansap.submissiondicoding.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.riansap.submissiondicoding.R
import com.riansap.submissiondicoding.databinding.ActivityAboutBinding

/**
 * AboutActivity
 *
 * Aktivitas ini digunakan untuk menampilkan halaman "About" dari aplikasi.
 * Halaman ini memiliki toolbar dengan tombol navigasi kembali untuk kembali ke aktivitas sebelumnya.
 */
class AboutActivity : AppCompatActivity() {

    // Menggunakan View Binding untuk mengakses elemen UI dengan lebih aman dan tanpa boilerplate
    private lateinit var binding: ActivityAboutBinding

    /**
     * onCreate - Metode utama yang dipanggil saat aktivitas dibuat.
     * Mengatur tampilan, toolbar, dan menangani navigasi kembali.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengikat elemen UI dari layout dengan View Binding, ini membuat kode lebih aman dan mengurangi risiko NullPointerException
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur Toolbar sebagai ActionBar untuk navigasi dan header
        setSupportActionBar(binding.toolbar)

        // Mengatur properti Toolbar dengan judul dan tombol navigasi kembali
        supportActionBar?.apply {
            title = "About" // Mengatur judul Toolbar
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol kembali
        }

        // Menangani aksi klik pada tombol kembali di Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish() // Mengakhiri aktivitas dan kembali ke halaman sebelumnya
        }
    }
}
