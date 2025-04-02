package com.kunbogroup.prayerwarriors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.kunbogroup.prayerwarriors.ui.theme.PrayerWarriorsTheme
import com.kunbogroup.prayerwarriors.viewmodel.AuthViewModel
import com.kunbogroup.prayerwarriors.ui.navigation.PrayerApp
import com.google.firebase.auth.FirebaseAuth

val auth = FirebaseAuth.getInstance()

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrayerWarriorsTheme {
                // Launch the PrayerApp NavGraph
                PrayerApp()
            }
        }
    }
}