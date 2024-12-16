package com.riansap.submissiondicoding.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.riansap.submissiondicoding.databinding.ActivityMainBinding
import com.riansap.submissiondicoding.model.Fruit
import com.riansap.submissiondicoding.repository.FruitRepository
import com.riansap.submissiondicoding.view.adapter.FruitAdapter
import com.riansap.submissiondicoding.viewmodel.MainViewModel
import com.riansap.submissiondicoding.viewmodel.ViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Gunakan ViewModelFactory untuk memberikan parameter
    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory(FruitRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonUser.setOnClickListener {
            // Logika saat tombol ditekan
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }


        val adapter = FruitAdapter { fruit ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("fruit_data", fruit)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        mainViewModel.fruits.observe(this) { fruits ->
            adapter.submitList(fruits)
        }

        mainViewModel.errorMessage.observe(this) { error ->
            // Tangani error jika diperlukan
        }

        mainViewModel.fetchFruits()
    }
}

