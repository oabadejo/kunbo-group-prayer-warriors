package com.kunbogroup.prayerwarriors.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.kunbogroup.prayerwarriors.model.Prayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PrayerViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _trendingPrayers = MutableStateFlow<List<Prayer>>(emptyList())
    val trendingPrayers: StateFlow<List<Prayer>> = _trendingPrayers

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadTrendingPrayers()
    }

    fun loadTrendingPrayers(onFailure: (Exception) -> Unit = {}) {
        _isLoading.value = true
        fetchTrendingPrayers(onFailure)
    }

    private fun fetchTrendingPrayers(onFailure: (Exception) -> Unit) {
        firestore.collection("prayers")
            .get()
            .addOnSuccessListener { result ->
                val prayers = result.mapNotNull { it.toObject(Prayer::class.java) }
                _trendingPrayers.value = prayers
                _isLoading.value = false
            }
            .addOnFailureListener { exception ->
                Log.e("PrayerViewModel", "Error fetching prayers", exception)
                _isLoading.value = false
            }
    }
}
