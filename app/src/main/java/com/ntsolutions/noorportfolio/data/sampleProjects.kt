// File: data/projectsList.kt
package com.ntsolutions.noorportfolio.data

val sampleProjects = listOf(
    Project(
        id = 1,
        title = "Weather App",
        description = "Kotlin + Retrofit + Coroutines — simple weather app.",
        imageRes = android.R.drawable.ic_menu_compass,
        githubUrl = "https://github.com/HifzaPrinces/EchoPost.git"
    ),
    Project(
        id = 2,
        title = "Notes App",
        description = "Jetpack Compose + Room + MVVM.",
        imageRes = android.R.drawable.ic_menu_edit,
        githubUrl = "https://github.com/HifzaPrinces/SmartNotes.git"
    ),
    Project(
        id = 3,
        title = "Portfolio App",
        description = "This app — built with Jetpack Compose.",
        imageRes = android.R.drawable.ic_menu_gallery,
        githubUrl = "https://github.com/HifzaPrinces/QuickStay-FullStack.git"
    )
)
