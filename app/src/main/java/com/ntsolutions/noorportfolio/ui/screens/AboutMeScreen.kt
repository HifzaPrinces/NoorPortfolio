package com.ntsolutions.noorportfolio.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun AboutMeScreen(navController: NavController) {
    var visible by remember { mutableStateOf(false) }

    // Animate visibility on screen load
    LaunchedEffect(Unit) {
        delay(300)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Text(
                text = "About Me",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Hi, I'm Hafzah — an Android developer. I build clean apps with Kotlin, Jetpack Compose, Coroutines and Firebase.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "Skills",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "• Kotlin\n• Jetpack Compose\n• Coroutines\n• Firebase\n• REST APIs\n• Postman\n• ReactNative",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
