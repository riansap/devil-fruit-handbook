package com.riansap.submissiondicoding.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.riansap.submissiondicoding.R
import com.riansap.submissiondicoding.databinding.ActivityDetailBinding
import com.riansap.submissiondicoding.model.Fruit
import com.squareup.picasso.Picasso

/**
 * DetailActivity
 *
 * Aktivitas ini digunakan untuk menampilkan detail informasi tentang buah yang dipilih
 * dari aktivitas sebelumnya. Informasi yang ditampilkan meliputi nama buah, nama latin,
 * tipe buah, deskripsi, dan gambar dari URL menggunakan Picasso.
 * Menggunakan Intent sesuai dengan kriteria submission.
 */
class DetailActivity : AppCompatActivity() {

    // Menggunakan View Binding untuk mengakses elemen UI dengan lebih aman
    private lateinit var binding: ActivityDetailBinding

    /**
     * onCreate - Method utama yang dipanggil saat aktivitas dibuat.
     * Mengatur tampilan, memuat data dari Intent, dan mengatur UI.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengikat elemen UI dari layout dengan View Binding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur Toolbar sebagai ActionBar untuk navigasi dan header
        // Dipakai sebagai header aktivitas dan berfungsi sebagai navigasi ke aktivitas sebelumnya
        setSupportActionBar(binding.toolbar)

        // Mengambil data buah dari Intent yang dikirim dari aktivitas sebelumnya
        val fruit = intent.getParcelableExtra<Fruit>("fruit_data")

        // Menampilkan data buah di elemen TextView jika data tersedia
        binding.textViewFruitName.text = fruit?.name
        binding.textViewRomanName.text = fruit?.roman_name
        binding.textViewFruitType.text = fruit?.type
        binding.textViewDescription.text = fruit?.description

        // Mengatur Toolbar dengan judul dan tombol navigasi kembali
        supportActionBar?.apply {
            title = fruit?.roman_name // Mengatur judul Toolbar dari nama latin buah secara dinamis
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol kembali
        }

        // Menangani aksi klik pada tombol kembali di Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish() // Mengakhiri aktivitas dan kembali ke aktivitas sebelumnya
        }

        // Memuat gambar dari URL menggunakan Picasso
        Picasso.get()
            .load(fruit?.filename) // URL gambar dari properti 'filename'
            .placeholder(R.drawable.placeholder) // Gambar placeholder saat gambar sedang dimuat
            .error(R.drawable.placeholder) // Gambar placeholder jika terjadi kesalahan saat memuat
            .fit() // Memastikan gambar disesuaikan ukurannya dengan ruang yang tersedia
            .centerInside() // Menyesuaikan gambar agar tampil dengan skala yang benar
            .into(binding.imageViewFruit) // Memuat gambar ke dalam ImageView
    }
}
