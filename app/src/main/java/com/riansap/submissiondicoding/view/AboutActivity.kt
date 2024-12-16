package com.riansap.submissiondicoding.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.riansap.submissiondicoding.R
import com.riansap.submissiondicoding.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atur Toolbar sebagai header dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "About"
            setDisplayHomeAsUpEnabled(true) // Tombol back
        }

        binding.toolbar.setNavigationOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }
    }
}
