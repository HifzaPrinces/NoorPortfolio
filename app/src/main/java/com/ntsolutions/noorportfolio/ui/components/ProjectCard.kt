package com.ntsolutions.noorportfolio.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ntsolutions.noorportfolio.data.Project

@Composable
fun ProjectCard(project: Project) {
    val ctx = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF8AB0E0)  // Light blue background
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = project.imageRes),
                contentDescription = project.title,
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black // Dark text for contrast
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = project.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (!project.githubUrl.isNullOrEmpty()) {
                    Button(onClick = {
                        ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(project.githubUrl)))
                    }) {
                        Text("View on GitHub")
                    }
                }
            }
        }
    }
}
