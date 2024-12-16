package com.riansap.submissiondicoding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riansap.submissiondicoding.model.Fruit
import com.riansap.submissiondicoding.repository.FruitRepository
import kotlinx.coroutines.launch

class MainViewModel(private val fruitRepository: FruitRepository) : ViewModel() {
    private val _fruits = MutableLiveData<List<Fruit>>()
    val fruits: LiveData<List<Fruit>> get() = _fruits

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchFruits() {
        viewModelScope.launch {
            try {
                val fruits = fruitRepository.getFruits()
                _fruits.value = fruits
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi kesalahan ${e.message}"
            }
        }
    }
}
