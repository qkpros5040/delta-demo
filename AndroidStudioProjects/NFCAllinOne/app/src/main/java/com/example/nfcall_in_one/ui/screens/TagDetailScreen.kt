package com.example.nfcall_in_one.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.components.*
import com.example.nfcall_in_one.ui.theme.*

@Composable
fun TagDetailScreen(
    onBack: () -> Unit,
    onClone: () -> Unit
) {
    Scaffold(
        containerColor = Bg,
        topBar = {
            TopBar(
                title = "Tag",
                onBack = onBack,
                trailingIcon = Icons.Default.MoreHoriz
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Gradient hero card
            item {
                Spacer(Modifier.height(8.dp))
                TagHeroCard()
            }

            // Quick actions
            item {
                QuickActionsRow(onClone = onClone)
            }

            // Payload section
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    SectionLabel(text = "Payload")
                    Spacer(Modifier.height(10.dp))
                    CardContainer {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Link, contentDescription = null, tint = Accent, modifier = Modifier.size(16.dp))
                                Text("URI Record", color = TextMuted, fontSize = 12.sp)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "https://example.com/nfc-tag",
                                color = Accent,
                                fontSize = 13.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }

            // Activity timeline
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    SectionLabel(text = "Activity")
                    Spacer(Modifier.height(10.dp))
                    ActivityTimeline()
                }
            }
        }
    }
}

@Composable
private fun TagHeroCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(listOf(Accent, AccentDeep))
            )
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(AccentInk.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Nfc, contentDescription = null, tint = Accent, modifier = Modifier.size(24.dp))
                }
                Icon(Icons.Default.Star, contentDescription = null, tint = AccentInk.copy(alpha = 0.5f), modifier = Modifier.size(20.dp))
            }
            Text("apartment-key", color = AccentInk, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("04:AB:3D:22:F1:10:80", color = AccentInk.copy(alpha = 0.7f), fontSize = 12.sp, fontFamily = FontFamily.Monospace)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TagChip("NTAG215")
                TagChip("504 B")
                TagChip("NDEF")
            }
        }
    }
}

@Composable
private fun TagChip(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(AccentInk.copy(alpha = 0.3f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(label, color = AccentInk, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun QuickActionsRow(onClone: () -> Unit) {
    val actions = listOf(
        Triple("Copy", Icons.Default.ContentCopy, {}),
        Triple("Share", Icons.Default.Share, {}),
        Triple("Clone", Icons.Default.FileCopy, onClone),
        Triple("Delete", Icons.Default.Delete, {})
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        actions.forEach { (label, icon, action) ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (label == "Delete") Danger.copy(alpha = 0.1f) else SurfaceCard)
                        .border(1.dp, if (label == "Delete") Danger.copy(alpha = 0.2f) else Border, RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = action) {
                        Icon(
                            icon,
                            contentDescription = label,
                            tint = if (label == "Delete") Danger else TextMuted,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Text(label, color = if (label == "Delete") Danger else TextMuted, fontSize = 11.sp)
            }
        }
    }
}

@Composable
private fun ActivityTimeline() {
    val events = listOf(
        Triple("Read", "Today 2:31 PM", Icons.Default.Nfc),
        Triple("Written", "Yesterday 10:14 AM", Icons.Default.Edit),
        Triple("Created", "3 days ago", Icons.Default.Add)
    )
    CardContainer {
        events.forEachIndexed { index, (action, time, icon) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Accent.copy(alpha = if (index == 0) 1f else 0.4f))
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(action, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
                Text(time, color = TextDim, fontSize = 11.sp)
                Icon(icon, contentDescription = null, tint = TextDim, modifier = Modifier.size(14.dp))
            }
            if (index < events.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Border,
                    thickness = 1.dp
                )
            }
        }
    }
}
