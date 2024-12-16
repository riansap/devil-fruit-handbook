package com.riansap.submissiondicoding.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class Fruit
 * Merepresentasikan data buah-buahan Iblis di dalam aplikasi.
 *
 * @property id Unique identifier untuk setiap buah. Tidak boleh null.
 * @property name Nama buah dalam bahasa english. Dapat bernilai null jika data tidak tersedia.
 * @property roman_name Nama buah dalam tulisan latin. Dapat bernilai null.
 * @property description Deskripsi singkat tentang buah. Dapat bernilai null.
 * @property type Jenis atau kategori buah (contoh: 'exotic', 'citrus'). Dapat bernilai null.
 * @property filename Nama file gambar yang terkait dengan buah. Dapat bernilai null.
 */
@Parcelize
data class Fruit(
    val id: String,            // ID unik untuk setiap buah, wajib diisi.
    val name: String?,         // Nama buah dalam bahasa english (opsional).
    val roman_name: String?,   // Nama buah dalam latin (opsional).
    val description: String?,  // Deskripsi buah (opsional).
    val type: String?,         // Jenis/kategori buah (opsional).
    val filename: String?      // Nama file gambar buah (opsional).
) : Parcelable
