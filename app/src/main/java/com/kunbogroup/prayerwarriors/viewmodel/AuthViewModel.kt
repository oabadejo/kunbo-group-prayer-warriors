package com.kunbogroup.prayerwarriors.viewmodel

import com.google.firebase.firestore.FirebaseFirestore
import com.kunbogroup.prayerwarriors.data.UserProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _currentUser = MutableLiveData<FirebaseUser?>(auth.currentUser)
    val currentUser: LiveData<FirebaseUser?> = _currentUser

    fun signUp(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    _currentUser.value = user

                    val profile = UserProfile(
                        uid = user?.uid ?: "",
                        name = name,
                        email = email
                    )

                    firestore.collection("users")
                        .document(profile.uid)
                        .set(profile)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { e -> onFailure(e) }

                } else {
                    onFailure(task.exception ?: Exception("Unknown error"))
                }
            }
    }

    fun signIn(
        email: String,
        password: String,
        onFailure: (Exception) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _currentUser.value = auth.currentUser
                } else {
                    onFailure(task.exception ?: Exception("Unknown error"))
                }
            }
    }

    fun signOut() {
        auth.signOut()
        _currentUser.value = null
    }
}
