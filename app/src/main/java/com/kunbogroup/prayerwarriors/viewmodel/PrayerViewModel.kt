package com.kunbogroup.prayerwarriors.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.kunbogroup.prayerwarriors.model.Prayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrayerViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _prayers = MutableStateFlow<List<Prayer>>(emptyList())
    val prayers: StateFlow<List<Prayer>> = _prayers

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchPrayers()
    }

    private fun fetchPrayers() {
        _isLoading.value = true
        viewModelScope.launch {
            firestore.collection("prayers")
                .get()
                .addOnSuccessListener { result ->
                    val prayerList = result.mapNotNull { document ->
                        document.toObject(Prayer::class.java)
                    }
                    _prayers.value = prayerList
                    _isLoading.value = false
                }
                .addOnFailureListener { exception ->
                    // Handle the error appropriately in your app
                    _isLoading.value = false
                }
        }
    }
}
