package com.kunbogroup.prayerwarriors.model

data class Prayer(
    val id: String = "", // Firestore document ID or unique identifier for the prayer
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val timestamp: Long = System.currentTimeMillis() // Timestamp to store when the prayer was created
)
