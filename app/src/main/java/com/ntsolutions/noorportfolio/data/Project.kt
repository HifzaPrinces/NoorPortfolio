package com.ntsolutions.noorportfolio.data

// File: data/Project.kt

import androidx.annotation.DrawableRes

data class Project(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val githubUrl: String? = null
)
