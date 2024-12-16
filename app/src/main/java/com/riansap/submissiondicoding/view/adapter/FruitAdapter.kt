package com.riansap.submissiondicoding.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import com.riansap.submissiondicoding.databinding.ItemFruitBinding
import com.riansap.submissiondicoding.model.Fruit

/**
 * Adapter untuk menampilkan daftar buah dalam RecyclerView.
 * Menggunakan ListAdapter untuk optimasi pembaruan daftar dengan bantuan DiffUtil.
 *
 * @param onClick Lambda function yang dipanggil saat item dalam daftar diklik.
 */
class FruitAdapter(private val onClick: (Fruit) -> Unit) :
    ListAdapter<Fruit, FruitAdapter.ViewHolder>(DiffCallback) {

    /**
     * Objek DiffCallback untuk membandingkan data dalam daftar.
     * Membantu menentukan apakah item lama dan baru adalah sama atau memiliki konten yang sama.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Fruit>() {
        /**
         * Membandingkan apakah dua item memiliki ID yang sama.
         */
        override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit) = oldItem.id == newItem.id

        /**
         * Membandingkan apakah konten dua item adalah sama.
         */
        override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit) = oldItem == newItem
    }

    /**
     * Dipanggil saat ViewHolder baru perlu dibuat.
     *
     * @param parent ViewGroup induk tempat ViewHolder dibuat.
     * @param viewType Tipe view (tidak digunakan saat inikarena hanya ada satu jenis tampilan).
     * @return ViewHolder yang baru dibuat.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Dipanggil untuk menghubungkan data ke ViewHolder.
     *
     * @param holder ViewHolder yang akan dihubungkan dengan data.
     * @param position Posisi item dalam daftar.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Kelas ViewHolder untuk mengelola tampilan dan data setiap item dalam daftar.
     *
     * @param binding Binding objek untuk mengakses elemen dalam layout item.
     */
    inner class ViewHolder(private val binding: ItemFruitBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        /**
         * Menghubungkan data `Fruit` dengan tampilan.
         *
         * @param fruit Objek `Fruit` yang akan ditampilkan.
         */
        fun bind(fruit: Fruit) {
            // Menyambungkan data langsung ke elemen binding
            binding.fruit = fruit
            binding.executePendingBindings()

            // Memuat gambar menggunakan Picasso
            Picasso.get()
                .load(fruit.filename) // URL gambar
                .placeholder(com.riansap.submissiondicoding.R.drawable.placeholder) // Gambar placeholder
                .into(binding.imageViewFruit) // Elemen ImageView

            // Menambahkan listener onClick pada item
            itemView.setOnClickListener { onClick(fruit) }
        }
    }
}
