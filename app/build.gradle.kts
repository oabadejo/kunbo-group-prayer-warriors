plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")

    id("com.google.dagger.hilt.android") version "2.50"
    kotlin("kapt")
}

android {
    namespace = "com.kunbogroup.prayerwarriors"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kunbogroup.prayerwarriors"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    hilt {
        enableAggregatingTask = false
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // BOM for all Compose dependencies
    implementation(platform(libs.androidx.compose.bom.v20250200))

    implementation(libs.ui)
    implementation(libs.material3)
    implementation(libs.navigation.compose)
    //implementation(libs.androidx.navigation.compose)


    // UI and tooling
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)

    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4)

    // Import the Firebase BoM
    implementation(platform(libs.firebase.bom))

    //Import the Firebase Auth
    implementation(libs.firebase.auth.ktx)

    implementation(libs.firebase.firestore)


    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation(libs.firebase.analytics)

    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries

    // Add the Material Icons Extended dependency
    implementation(libs.androidx.material.icons.extended) // Use the latest version

    // Hilt main libraries
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Hilt with Jetpack Compose navigation
    implementation(libs.androidx.hilt.navigation.compose)
}
