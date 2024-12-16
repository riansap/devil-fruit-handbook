package com.riansap.submissiondicoding.view

import android.content.Intent
import android.os.Bundle
import android.content.DialogInterface;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.riansap.submissiondicoding.R
import com.riansap.submissiondicoding.databinding.ActivityMainBinding
import com.riansap.submissiondicoding.view.adapter.FruitAdapter
import com.riansap.submissiondicoding.viewmodel.MainViewModel
import com.riansap.submissiondicoding.viewmodel.ViewModelFactory
import com.riansap.submissiondicoding.repository.FruitRepository

/**
 * MainActivity adalah aktivitas utama aplikasi ini yang bertanggung jawab untuk:
 * 1. Menampilkan daftar buah dari API dalam RecyclerView menggunakan adapter.
 * 2. Mengatur komunikasi dengan ViewModel dan menangani observasi perubahan data.
 * 3. Menangani menu navigasi ke aktivitas lain (seperti AboutActivity).
 * 4. Menampilkan indikator loading, pesan error, dan empty state.
 */
class MainActivity : AppCompatActivity() {

    // Menghubungkan dengan layout menggunakan View Binding
    private lateinit var binding: ActivityMainBinding

    // Menggunakan ViewModel untuk mengatur data dan komunikasi dengan Repository
    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory(FruitRepository())
    }

    /**
     * Metode ini dipanggil saat aktivitas pertama kali dibuat.
     * Di sini kita mengatur tampilan awal, menu, adapter, dan memulai observasi data dari ViewModel.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengatur tema agar selalu Day/Light mode
        if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Mengikat layout dengan View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur Toolbar sebagai ActionBar
        setSupportActionBar(binding.toolbar)

        // Membuat adapter dengan listener klik
        val adapter = FruitAdapter { fruit ->
            // Navigasi ke DetailActivity ketika item diklik
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("fruit_data", fruit) // Mengirim data buah yang diklik
            }
            startActivity(intent)
        }

        // Mengatur RecyclerView dengan LinearLayoutManager dan adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Menampilkan progress bar sebagai indikator loading saat memuat data dari ViewModel
        showLoading(true)

        // Observasi perubahan data dari ViewModel
        mainViewModel.fruits.observe(this) { fruits ->
            // Memuat daftar buah ke dalam adapter dan menghilangkan indikator loading
            adapter.submitList(fruits)
            showLoading(false)
            showEmptyState(fruits.isEmpty()) // Tampilkan pesan jika daftar kosong
        }

        // Observasi error yang mungkin terjadi
        mainViewModel.errorMessage.observe(this) { error ->
            showLoading(false) // Menghilangkan indikator loading
            showError(error) // Tampilkan pesan error dengan Snackbar
        }

        // Memulai pemanggilan data dari ViewModel
        mainViewModel.fetchFruits()
    }

    /**
     * Membuat menu di Toolbar.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) // Mengikat menu dari resource menu_main.xml
        return true
    }

    /**
     * Menangani klik pada item menu.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                // Navigasi ke halaman AboutActivity saat menu "About" diclick
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item) // Jika menu lain diclick, tetap jalankan default
        }
    }

    /**
     * Menampilkan atau menyembunyikan indikator loading (ProgressBar).
     * @param isLoading: Tipe boolean untuk menunjukkan apakah data sedang dimuat.
     */
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.loadingText.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    /**
     * Menampilkan pesan error kepada pengguna melalui Snackbar.
     * @param message: Pesan error yang akan ditampilkan.
     */
    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    /**
     * Menampilkan pesan jika daftar kosong.
     * @param isEmpty: boolean untuk mengecek apakah daftar kosong.
     */
    private fun showEmptyState(isEmpty: Boolean) {
        binding.emptyStateText.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    /**
     * Menangani aksi tombol back yang ditekan oleh pengguna.
     */
    override fun onBackPressed() {
        // Membuat dialog konfirmasi
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                super.onBackPressed() // Keluar aplikasi jika "Ya" ditekan
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss() // Tutup dialog tanpa melakukan aksi
            }
            .show()
    }
}
