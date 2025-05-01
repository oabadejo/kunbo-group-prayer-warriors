package com.kunbogroup.prayerwarriors.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kunbogroup.prayerwarriors.viewmodel.PrayerViewModel

@Composable
fun TrendingScreen(navController: NavHostController,
    viewModel: PrayerViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val prayers by viewModel.trendingPrayers.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTrendingPrayers(
            onFailure = { exception ->
                Toast.makeText(context, "Failed to load prayers: ${exception.message}", Toast.LENGTH_LONG).show()
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            if (prayers.isEmpty()) {
                Text("No trending prayers found.")
            } else {
                prayers.forEach { prayer ->
                    Text(text = prayer.title) // Display the title of each prayer
                    Text(text = prayer.content) // Optionally display the content or other details
                }
            }
        }
    }
}
