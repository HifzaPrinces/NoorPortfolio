package com.ntsolutions.noorportfolio.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun ContactScreen(navController: NavController) {
    val ctx = LocalContext.current
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        visible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Contact",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Email Button - Slide from Left with hover color swap
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(600)
            )
        ) {
            var isHovered by remember { mutableStateOf(false) }
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:hifzajabeen32@gmail.com")
                    }
                    ctx.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isHovered) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                    contentColor = if (isHovered) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event: PointerEvent = awaitPointerEvent()
                            isHovered = event.type == PointerEventType.Enter
                        }
                    }
                }
            ) {
                Text("Email: hifzajabeen32@gmail.com")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // GitHub Button - Slide from Right with hover color swap
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(600)
            )
        ) {
            var isHovered by remember { mutableStateOf(false) }
            Button(
                onClick = {
                    val uri = Uri.parse("https://github.com/hafzah")
                    ctx.startActivity(Intent(Intent.ACTION_VIEW, uri))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isHovered) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                    contentColor = if (isHovered) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event: PointerEvent = awaitPointerEvent()
                            isHovered = event.type == PointerEventType.Enter
                        }
                    }
                }
            ) {
                Text("GitHub")
            }
        }
    }
}
