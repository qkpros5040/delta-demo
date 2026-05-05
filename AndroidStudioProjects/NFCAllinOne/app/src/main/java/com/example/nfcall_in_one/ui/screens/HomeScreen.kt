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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.components.*
import com.example.nfcall_in_one.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateToRead: () -> Unit,
    onNavigateToWrite: () -> Unit,
    onNavigateToClone: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToTagDetail: () -> Unit
) {
    Scaffold(
        containerColor = Bg,
        bottomBar = {
            BottomNavBar(
                active = NavDestination.HOME,
                onHome = {},
                onWrite = onNavigateToWrite,
                onHistory = onNavigateToHistory
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // Header
            item {
                HomeHeader(onSettings = onNavigateToSettings)
            }
            // NFC Status Chip
            item {
                Spacer(Modifier.height(4.dp))
                Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                    NfcStatusChip()
                }
            }
            // Hero card
            item {
                Spacer(Modifier.height(20.dp))
                HeroCard()
            }
            // Action grid
            item {
                Spacer(Modifier.height(24.dp))
                ActionGrid(
                    onRead = onNavigateToRead,
                    onWrite = onNavigateToWrite,
                    onClone = onNavigateToClone,
                    onVerify = {}
                )
            }
            // Recent activity
            item {
                Spacer(Modifier.height(28.dp))
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    SectionLabel(text = "Recent activity", action = "See all", onAction = onNavigateToHistory)
                }
            }
            item {
                Spacer(Modifier.height(12.dp))
                RecentActivityList(onTagDetail = onNavigateToTagDetail)
            }
        }
    }
}

@Composable
private fun HomeHeader(onSettings: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                "Good evening, Alex",
                color = TextMuted,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                "NFC All-in-One",
                color = TextPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onSettings) {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = TextMuted)
            }
            // Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(listOf(Accent, AccentDeep))),
                contentAlignment = Alignment.Center
            ) {
                Text("AP", color = AccentInk, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun NfcStatusChip() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0x1A4ADE80))
            .border(1.dp, Color(0x334ADE80), RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(GreenDot)
        )
        Text("NFC ready", color = GreenDot, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun HeroCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Accent.copy(alpha = 0.08f),
                        Accent.copy(alpha = 0.02f),
                        Color.Transparent
                    )
                )
            )
            .background(SurfaceCard)
            .border(1.dp, BorderStrong, RoundedCornerShape(20.dp))
            .padding(24.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Glow ring
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Accent.copy(alpha = 0.08f)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Accent.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Accent.copy(alpha = 0.18f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.GraphicEq,
                            contentDescription = null,
                            tint = Accent,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(
                "Hold a tag to the back of your phone",
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Bring any NFC tag close to start",
                color = TextDim,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun ActionGrid(
    onRead: () -> Unit,
    onWrite: () -> Unit,
    onClone: () -> Unit,
    onVerify: () -> Unit
) {
    val actions = listOf(
        ActionItem("Read", Icons.Default.Nfc, false, onRead),
        ActionItem("Write", Icons.Default.Edit, false, onWrite),
        ActionItem("Clone", Icons.Default.FileCopy, true, onClone),
        ActionItem("Verify", Icons.Default.Verified, false, onVerify)
    )
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        for (row in actions.chunked(2)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.forEach { action ->
                    ActionCard(
                        action = action,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

data class ActionItem(
    val label: String,
    val icon: ImageVector,
    val isPrimary: Boolean,
    val onClick: () -> Unit
)

@Composable
private fun ActionCard(action: ActionItem, modifier: Modifier = Modifier) {
    val bgColor = if (action.isPrimary) AccentInk else SurfaceCard
    val borderColor = if (action.isPrimary) Accent else Border
    val iconTint = if (action.isPrimary) Accent else TextMuted

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable { action.onClick() }
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (action.isPrimary) Accent.copy(alpha = 0.15f) else SurfaceHi),
                contentAlignment = Alignment.Center
            ) {
                Icon(action.icon, contentDescription = action.label, tint = iconTint, modifier = Modifier.size(18.dp))
            }
            Text(
                action.label,
                color = if (action.isPrimary) Accent else TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun RecentActivityList(onTagDetail: () -> Unit) {
    val items = listOf(
        HistoryItem("1", "apartment-key", "NTAG215", "04:AB:3D:22:F1:10:80", Icons.Default.Nfc, "2m ago", "Read", Accent),
        HistoryItem("2", "wifi-home", "NTAG213", "https://home.local/wifi", Icons.Default.Wifi, "1h ago", "Write", Warn),
        HistoryItem("3", "business-card", "MIFARE", "contact:john@example.com", Icons.Default.CreditCard, "3h ago", "Clone", TextMuted)
    )
    CardContainer(modifier = Modifier.padding(horizontal = 20.dp)) {
        items.forEachIndexed { index, item ->
            HistoryRow(item = item, onClick = onTagDetail)
            if (index < items.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Border,
                    thickness = 1.dp
                )
            }
        }
    }
}
