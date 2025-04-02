package com.kunbogroup.prayerwarriors.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.kunbogroup.prayerwarriors.ui.screens.TrendingScreen
import com.kunbogroup.prayerwarriors.ui.screens.PrayerAppFirstScreen
import com.kunbogroup.prayerwarriors.ui.screens.RequestPrayerScreen
import com.kunbogroup.prayerwarriors.ui.screens.OfferPrayerScreen
import com.kunbogroup.prayerwarriors.ui.screens.ProfileSetupScreen
import com.kunbogroup.prayerwarriors.ui.screens.SignInScreen

@Composable
fun PrayerApp() {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val currentUser by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(auth.currentUser) }

    NavHost(
        navController = navController,
        startDestination = "trending"
    ) {
        composable("trending") { TrendingScreen(navController) }
        composable("sign_in") { SignInScreen(navController) }
        composable("profile_setup") { ProfileSetupScreen(navController) }
        composable("home") {
            if (currentUser != null) {
                PrayerAppFirstScreen(navController)
            } else {
                navController.navigate("sign_in")
            }
        }
        composable("requestPrayer") {
            if (currentUser != null) {
                RequestPrayerScreen(navController)
            } else {
                navController.navigate("sign_in")
            }
        }
        composable("offerPrayer") {
            if (currentUser != null) {
                OfferPrayerScreen(navController)
            } else {
                navController.navigate("sign_in")
            }
        }
    }
}
