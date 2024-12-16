package com.riansap.submissiondicoding.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import com.riansap.submissiondicoding.databinding.ItemFruitBinding
import com.riansap.submissiondicoding.model.Fruit

class FruitAdapter(private val onClick: (Fruit) -> Unit) :
    ListAdapter<Fruit, FruitAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Fruit>() {
        override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemFruitBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(fruit: Fruit) {
            // Binding data
            binding.fruit = fruit
            binding.executePendingBindings()

            // Load image using Picasso
            Picasso.get()
                .load(fruit.filename)
                .placeholder(com.riansap.submissiondicoding.R.drawable.placeholder) // Placeholder harus ada di drawable
                .into(binding.imageViewFruit)

            itemView.setOnClickListener { onClick(fruit) }
        }
    }
}
