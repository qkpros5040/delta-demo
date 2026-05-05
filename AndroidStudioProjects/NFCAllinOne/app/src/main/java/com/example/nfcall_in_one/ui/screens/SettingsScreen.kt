package com.example.nfcall_in_one.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.components.*
import com.example.nfcall_in_one.ui.theme.*

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    // State for toggles
    var autoRead by remember { mutableStateOf(true) }
    var vibrate by remember { mutableStateOf(true) }
    var sound by remember { mutableStateOf(false) }
    var darkMode by remember { mutableStateOf(true) }
    var compactView by remember { mutableStateOf(false) }
    var analytics by remember { mutableStateOf(false) }
    var crashReports by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = Bg,
        topBar = { TopBar(title = "Settings", onBack = onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { Spacer(Modifier.height(4.dp)) }

            // Profile card
            item { ProfileCard() }

            // NFC section
            item {
                SectionLabel(text = "NFC")
                Spacer(Modifier.height(8.dp))
                CardContainer {
                    ToggleRow("Auto-read on detect", "Scan tags automatically", Icons.Default.Nfc, autoRead) { autoRead = it }
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ToggleRow("Vibrate on scan", "Haptic feedback", Icons.Default.Vibration, vibrate) { vibrate = it }
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ToggleRow("Sound on scan", "Audio cue", Icons.Default.VolumeUp, sound) { sound = it }
                }
            }

            // Appearance section
            item {
                SectionLabel(text = "Appearance")
                Spacer(Modifier.height(8.dp))
                CardContainer {
                    ToggleRow("Dark mode", "Always use dark theme", Icons.Default.DarkMode, darkMode) { darkMode = it }
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ToggleRow("Compact view", "Denser history list", Icons.Default.ViewList, compactView) { compactView = it }
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ChevronRow("Language", "English", Icons.Default.Language)
                }
            }

            // Privacy section
            item {
                SectionLabel(text = "Privacy")
                Spacer(Modifier.height(8.dp))
                CardContainer {
                    ToggleRow("Analytics", "Help improve the app", Icons.Default.BarChart, analytics) { analytics = it }
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ToggleRow("Crash reports", "Automatic error reporting", Icons.Default.BugReport, crashReports) { crashReports = it }
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ChevronRow("Privacy policy", null, Icons.Default.Security)
                }
            }

            // About section
            item {
                SectionLabel(text = "About")
                Spacer(Modifier.height(8.dp))
                CardContainer {
                    ChevronRow("Version", "1.0.0", Icons.Default.Info)
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ChevronRow("Rate app", null, Icons.Default.Star)
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ChevronRow("Contact support", null, Icons.Default.Email)
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ChevronRow("Licenses", null, Icons.Default.Article)
                }
            }
        }
    }
}

@Composable
private fun ProfileCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(listOf(AccentDeep, SurfaceCard))
            )
            .border(1.dp, Accent.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(listOf(Accent, AccentDeep))),
                contentAlignment = Alignment.Center
            ) {
                Text("AP", color = AccentInk, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text("Alex Park", color = TextPrimary, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    ProfileBadge("Pro")
                    ProfileBadge("2 years")
                    ProfileBadge("318 tags")
                }
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextDim, modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
private fun ProfileBadge(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(AccentInk.copy(alpha = 0.5f))
            .padding(horizontal = 7.dp, vertical = 3.dp)
    ) {
        Text(label, color = Accent, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun ChevronRow(
    label: String,
    value: String?,
    icon: ImageVector,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceHi),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = TextMuted, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(12.dp))
        Text(label, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
        if (value != null) {
            Text(value, color = TextDim, fontSize = 13.sp)
            Spacer(Modifier.width(4.dp))
        }
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextDim, modifier = Modifier.size(18.dp))
    }
}
