package com.riansap.submissiondicoding.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.riansap.submissiondicoding.R
import com.riansap.submissiondicoding.databinding.ActivityDetailBinding
import com.riansap.submissiondicoding.model.Fruit
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data yang dikirim melalui intent
        val fruit = intent.getParcelableExtra<Fruit>("fruit_data")

        // Tampilkan data di UI
        binding.textViewFruitName.text = fruit?.name
        binding.textViewRomanName.text = fruit?.roman_name
        binding.textViewFruitType.text = fruit?.type
        binding.textViewDescription.text = fruit?.description

        // Atur Toolbar sebagai header dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = fruit?.roman_name // Set the title dynamically
            setDisplayHomeAsUpEnabled(true) // Tombol back
        }

        binding.toolbar.setNavigationOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }

        // Gunakan Picasso untuk memuat gambar dari URL
        Picasso.get()
            .load(fruit?.filename) // URL gambar
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .fit() // Sesuaikan ukuran secara otomatis
            .centerInside() // Pastikan tampil seperti yang diatur dengan scaleType
            .into(binding.imageViewFruit)
    }
}
