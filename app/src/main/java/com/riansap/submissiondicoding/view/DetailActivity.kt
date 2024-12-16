package com.riansap.submissiondicoding.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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

        // Menangani aksi klik pada tombol bagikan
        binding.buttonShare.setOnClickListener {
            if (fruit != null) {
                shareFruitDetails(fruit)
            }
        }

        // Menampilkan data buah di elemen TextView jika data tersedia
        fruit?.let {
            binding.textViewFruitName.text = it.name
            binding.textViewRomanName.text = it.roman_name
            binding.textViewFruitType.text = it.type
            binding.textViewDescription.text = it.description

        // Mengatur Toolbar dengan judul dan tombol navigasi kembali
        supportActionBar?.apply {
            title = it.roman_name ?: "Detail Buah Iblis" // Mengatur judul Toolbar dari nama latin buah secara dinamis
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol kembali
        }

        // Menangani aksi klik pada tombol kembali di Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish() // Mengakhiri aktivitas dan kembali ke aktivitas sebelumnya
        }

        // Memuat gambar dari URL menggunakan Picasso
        Picasso.get()
            .load(it.filename) // URL gambar dari properti 'filename'
            .placeholder(R.drawable.placeholder) // Gambar placeholder saat gambar sedang dimuat
            .error(R.drawable.placeholder) // Gambar placeholder jika terjadi kesalahan saat memuat
            .fit() // Memastikan gambar disesuaikan ukurannya dengan ruang yang tersedia
            .centerInside() // Menyesuaikan gambar agar tampil dengan skala yang benar
            .into(binding.imageViewFruit) // Memuat gambar ke dalam ImageView



            // Logika tambahan untuk menampilkan explanation dan examples berdasarkan tipe buah
            val type = it.type
            if (type != null) {
                when {
                    type.contains("Zoan", true) -> showExplanationAndExamples(
                        "Zoan-type Devil Fruits give users the ability to transform into specific animals, a hybrid of human and animal, or the full animal itself.\n\nThis ability provides users with enhanced physical attributes like speed, strength, and agility, making them highly versatile in hand-to-hand combat or strategic movement.\n\nZoans are especially useful in combat due to their ability to shift forms and adapt to different fighting situations.\nFor example, a user can adopt a full reindeer-like appearance to charge enemies with powerful physical force or assume a hybrid form to remain agile while fighting.",
                        " Examples of Zoan users include Tony Tony Chopper, who can transform into a reindeer or a reindeer-human hybrid, and Rob Lucci, who uses his Zoan powers to take on a tiger-like form."
                    )

                    type.contains("Logia", true) -> showExplanationAndExamples(
                        "Logia-type Devil Fruits are considered the rarest and most powerful type of Devil Fruit. They grant the user the ability to create, control, and transform their body into a natural element (e.g., fire, sand, smoke, or ice).\n\nLogia users can phase through physical attacks by turning into their respective elements, making them nearly impervious to most forms of damage unless the opponent uses Haki or specialized weapons.",
                        "For example, Portgas D. Ace possessed the ability to control fire with his Mera Mera no Mi, while Crocodile wielded sand-based powers using his Suna Suna no Mi.\n\nLogia powers offer extreme destructive power, as they can manipulate large-scale environmental phenomena, such as creating devastating storms or sandstorms."
                    )

                    type.contains("Paramecia", true) -> showExplanationAndExamples(
                        "Paramecia-type Devil Fruits represent the most versatile and diverse category of Devil Fruits. These powers allow users to manipulate their bodies, the environment, or manifest unique superhuman abilities.\n\nUnlike Zoans or Logias, Paramecia does not involve transforming into an animal or natural element but instead focuses on a wide range of unique, creative powers.",
                        "Examples include Monkey D. Luffy, who uses his Gomu Gomu no Mi (now Hito Hito no Mi, Model: Nika) to stretch his body like rubber, and Donquixote Doflamingo, who uses his String-String Fruit to control and manipulate strings for combat or utility purposes.\n\nParamecia users exhibit diverse powers ranging from manipulating physical traits to creating unusual abilities, offering strategic adaptability in combat.\n"
                    )

                    type.contains("Smile", true) -> showExplanationAndExamples(
                        "Smile Devil Fruits are synthetic or artificial Devil Fruits that mimic Zoan powers by allowing the user to transform into specific animals or a hybrid animal form.\n\nWhile they grant powerful transformations, Smile Fruits have numerous side effects and are less reliable than natural Zoan types. Users often struggle with unstable transformations or reduced mastery over their abilities.",
                        "Characters like Holdem and Sheepshead are notable examples of Smile users. Despite their combat potential, Smile users face challenges like loss of control or the inability to fully adapt to combat situations."
                    )

                    else -> showExplanationAndExamples(
                        "Devil Fruits have yet to be identified.\n\nSome Devil Fruits defy conventional classifications like Zoan, Logia, or Paramecia. These unknown or unclassified fruits represent rare and mysterious abilities, often hinting at ancient or powerful potential.",
                        "Examples include rare abilities wielded by figures like Laffitte and other characters with unexplained powers.\n\nThese unknown Devil Fruits may allow their users to manifest abilities that aren’t yet understood or categorized within the known Devil Fruit types. They pose a wildcard in combat or exploration, as their properties are unpredictable and unique."
                    )
                }
            }
        }
    }
    /**
     * Menampilkan penjelasan (explanation) dan contoh (examples) pada UI.
     *
     * @param explanation Penjelasan tentang tipe buah iblis
     * @param examples Contoh karakter pengguna buah iblis tersebut
     */
    private fun showExplanationAndExamples(explanation: String, examples: String) {
        binding.textViewExplanation.apply {
            text = explanation
            visibility = View.VISIBLE
        }
        binding.textViewExamples.apply {
            text = examples
            visibility = View.VISIBLE
        }
    }

    /**
     * Fungsi untuk membagikan informasi buah menggunakan intent SHARE
     */
    private fun shareFruitDetails(fruit: Fruit?) {
        if (fruit == null) {
            // Log atau tunjukkan pesan error jika tidak ada data untuk dibagikan
            Log.e("DetailActivity", "Data buah tidak tersedia untuk dibagikan!")
            return
        }

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Detail Buah: ${fruit.name}")
            putExtra(Intent.EXTRA_TEXT,
                "Nama Buah: ${fruit.name}\n" +
                        "Nama Latin: ${fruit.roman_name ?: "Tidak diketahui"}\n" +
                        "Tipe Buah: ${fruit.type ?: "Tidak diketahui"}\n" +
                        "Deskripsi: ${fruit.description ?: "Tidak tersedia"}"
            )
        }

        try {
            // Memeriksa apakah ada aplikasi untuk menangani intent share
            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
            } else {
                Log.e("DetailActivity", "No activity found to handle sharing intent.")
            }
        } catch (e: Exception) {
            Log.e("DetailActivity", "Error while trying to share details", e)
        }
    }


}
