package com.ntsolutions.noorportfolio.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ntsolutions.noorportfolio.R
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFF0B3D91))
            .padding(top = 60.dp, start = 20.dp, end = 20.dp)
    ) {
        // ===== TOP HEADER AREA with Tabs + Icons =====
        TopHeaderWithIconTabs(navController)

        Spacer(modifier = Modifier.height(30.dp))

        // ===== PROFILE SECTION with Image Carousel and Animated Text =====
        ProfileSection()

        Spacer(modifier = Modifier.height(40.dp))

        // ===== CARDS SECTION =====
        Text(
            text = "What I Offer",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(3) { index ->
                OfferCard(
                    iconRes = when (index) {
                        0 -> R.drawable.mobileapp
                        1 -> R.drawable.ui
                        else -> R.drawable.conversation
                    },
                    title = when (index) {
                        0 -> "Mobile Apps"
                        1 -> "UI/UX Design"
                        else -> "Consulting"
                    },
                    description = when (index) {
                        0 -> "Building clean and scalable Android apps with Kotlin."
                        1 -> "Designing user-friendly interfaces using modern tools."
                        else -> "Helping you plan and execute your tech projects."
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // ===== Skills Section =====
        SkillsSection()

        Spacer(modifier = Modifier.height(40.dp))

        // ===== Testimonials Section =====
        TestimonialsSection()

        Spacer(modifier = Modifier.height(40.dp))

        // ===== Contact Section (instead of footer) =====
        ContactSection()
    }
}

@Composable
fun TopHeaderWithIconTabs(navController: NavController) {
    val tabs = listOf(
        TabItem("Projects", R.drawable.ic_compass),
        TabItem("About", R.drawable.profileuser),
        TabItem("Contact", R.drawable.telephone)
    )
    var selectedTabIndex by remember { mutableStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ntlogo),
            contentDescription = "NoorTech Logo",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = Color.White,
            indicator = {}, // Indicator hata di gayi yahan
            divider = {}, // Divider hata di gayi yahan
            modifier = Modifier.weight(1f)
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        when (tab.title) {
                            "Projects" -> navController.navigate("projects")
                            "About" -> navController.navigate("about")
                            "Contact" -> navController.navigate("contact")
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = tab.iconRes),
                            contentDescription = tab.title,
                            modifier = Modifier.size(20.dp), // Icon size thoda kam kiya hai
                            tint = if (selectedTabIndex == index) Color(0xFFADD8FF) else Color.White
                        )
                    },
                    text = {
                        Text(
                            tab.title,
                            color = if (selectedTabIndex == index) Color(0xFFADD8FF) else Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }
    }
}

data class TabItem(val title: String, val iconRes: Int)

// --- Rest of your composables below remain the same ---

@Composable
fun ProfileSection() {
    val imageAnim = remember { Animatable(-300f) }
    val textAnim = remember { Animatable(300f) }
    val context = LocalContext.current

    val images = listOf(R.drawable.employer/*, R.drawable.employer, R.drawable.employer*/)
    var currentImageIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        imageAnim.animateTo(0f, tween(700, easing = FastOutSlowInEasing))
        delay(200)
        textAnim.animateTo(0f, tween(700, easing = FastOutSlowInEasing))

        while (true) {
            delay(3000)
            currentImageIndex = (currentImageIndex + 1) % images.size
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = images[currentImageIndex]),
            contentDescription = "Hifza Noor",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .offset { IntOffset(imageAnim.value.toInt(), 0) }
        )

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier.offset { IntOffset(textAnim.value.toInt(), 0) }
        ) {
            val annotatedText = buildAnnotatedString {
                append("I’m ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Hifza Noor")
                }
                append(", an ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Android Developer")
                }
                append(" passionate about building sleek and efficient mobile ")
            }

            Text(
                text = annotatedText,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )

            AnimatedEllipsisText(baseText = "apps")
        }
    }
}

@Composable
fun AnimatedEllipsisText(baseText: String) {
    var dotCount by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            dotCount = (dotCount + 1) % 4
        }
    }

    Text(
        text = baseText + " ".repeat(1) + ".".repeat(dotCount),
        color = Color.White,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun OfferCard(iconRes: Int, title: String, description: String) {
    Card(
        modifier = Modifier
            .width(240.dp)
            .height(140.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFADD8FF))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "$title Icon",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0B3D91)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF0B3D91)
                )
            }
        }
    }
}

@Composable
fun SkillsSection() {
    Column {
        Text(
            text = "My Skills",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))

        val skills = listOf(
            "Kotlin" to 0.9f,
            "Jetpack Compose" to 0.85f,
            "UI/UX Design" to 0.7f,
            "Project Management" to 0.75f
        )

        skills.forEach { (skill, proficiency) ->
            Text(
                text = skill,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = proficiency,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Color(0xFFADD8FF),
                trackColor = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun TestimonialsSection() {
    Column {
        Text(
            text = "Testimonials",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))

        val testimonials = listOf(
            "Hifza's apps are top-notch! Clean code and great UI." to "Abid Khan",
            "Professional and punctual. Highly recommended!" to "Haadi Khan",
            "Excellent developer with great communication skills." to "Client X"
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(testimonials.size) { index ->
                Card(
                    modifier = Modifier
                        .width(280.dp)
                        .height(140.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFADD8FF))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "\"${testimonials[index].first}\"",
                            color = Color(0xFF0B3D91),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "- ${testimonials[index].second}",
                            color = Color(0xFF0B3D91),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContactSection() {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0B3D91))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Contact Me",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(24.dp))

        ContactInfoClickableRow(
            iconRes = R.drawable.mail,
            info = "hifzajabeen32@gmail.com",
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:hifzajabeen32@gmail.com")
                }
                context.startActivity(intent)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        ContactInfoClickableRow(
            iconRes = R.drawable.telephone,
            info = "+92 327 7668366",
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:+923277668366")
                }
                context.startActivity(intent)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        ContactInfoClickableRow(
            iconRes = R.drawable.location,
            info = "Lodhran, Pakistan",
            onClick = {
                val mapUri = Uri.parse("geo:0,0?q=Lodhran, Pakistan")
                val intent = Intent(Intent.ACTION_VIEW, mapUri)
                context.startActivity(intent)
            }
        )
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Facebook",
                modifier = Modifier
                    .size(36.dp)
                    .clickable { uriHandler.openUri("https://www.facebook.com/") }
            )
            Image(
                painter = painterResource(id = R.drawable.twitter),
                contentDescription = "Twitter",
                modifier = Modifier
                    .size(36.dp)
                    .clickable { uriHandler.openUri("https://www.twitter.com/") }
            )
            Image(
                painter = painterResource(id = R.drawable.instagram),
                contentDescription = "Instagram",
                modifier = Modifier
                    .size(36.dp)
                    .clickable { uriHandler.openUri("https://www.instagram.com/") }
            )
            Image(
                painter = painterResource(id = R.drawable.whatsapp),
                contentDescription = "WhatsApp",
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        val phoneNumber = "+923277668366"
                        val url = "https://wa.me/${phoneNumber.removePrefix("+")}"
                        uriHandler.openUri(url)
                    }
            )
        }
    }
}

@Composable
fun ContactInfoClickableRow(iconRes: Int, info: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = info,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}
