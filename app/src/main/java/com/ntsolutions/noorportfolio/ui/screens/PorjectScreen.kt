package com.ntsolutions.noorportfolio.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ntsolutions.noorportfolio.data.sampleProjects
import com.ntsolutions.noorportfolio.ui.components.ProjectCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Projects",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
            ) {
                items(sampleProjects) { project ->
                    ProjectCard(project = project)
                }
            }
        }
    )
}
