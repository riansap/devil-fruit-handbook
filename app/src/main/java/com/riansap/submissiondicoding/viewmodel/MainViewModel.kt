package com.riansap.submissiondicoding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riansap.submissiondicoding.model.Fruit
import com.riansap.submissiondicoding.repository.FruitRepository
import kotlinx.coroutines.launch

/**
 * MainViewModel
 * ViewModel untuk MainActivity yang bertugas mengelola data buah dan status error.
 *
 * @param fruitRepository Repository yang digunakan untuk mendapatkan data buah dari API.
 */
class MainViewModel(private val fruitRepository: FruitRepository) : ViewModel() {

    // LiveData untuk menyimpan daftar buah yang akan diobservasi oleh UI
    private val _fruits = MutableLiveData<List<Fruit>>()
    val fruits: LiveData<List<Fruit>> get() = _fruits

    // LiveData untuk menyimpan pesan error yang akan diobservasi oleh UI
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    /**
     * Memanggil data buah dari repository dan memperbarui LiveData.
     */
    fun fetchFruits() {
        // Meluncurkan coroutine di viewModelScope untuk operasi latar belakang
        viewModelScope.launch {
            try {
                // Memanggil fungsi repository untuk mendapatkan data buah
                val fruits = fruitRepository.getFruits()
                _fruits.value = fruits // Memperbarui data buah
            } catch (e: Exception) {
                // Memperbarui error message jika terjadi kesalahan
                _errorMessage.value = e.message ?: "Terjadi kesalahan: ${e.message}"
            }
        }
    }
}
