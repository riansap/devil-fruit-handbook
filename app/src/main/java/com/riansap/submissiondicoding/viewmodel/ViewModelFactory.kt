package com.riansap.submissiondicoding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riansap.submissiondicoding.repository.FruitRepository

/**
 * ViewModelFactory
 * Sebuah Factory untuk membuat instance ViewModel dengan parameter custom.
 *
 * @param repository Repository yang digunakan untuk ViewModel.
 */
class ViewModelFactory(private val repository: FruitRepository) : ViewModelProvider.Factory {

    /**
     * Membuat instance ViewModel berdasarkan kelas yang diberikan.
     *
     * @param modelClass Kelas dari ViewModel yang diminta.
     * @return Instance dari ViewModel.
     * @throws IllegalArgumentException jika kelas ViewModel tidak diketahui.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel class tidak diketahui: ${modelClass.name}")
    }
}
