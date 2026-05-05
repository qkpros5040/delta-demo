package com.example.nfcall_in_one.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.components.*
import com.example.nfcall_in_one.ui.theme.*

@Composable
fun HistoryScreen(
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToWrite: () -> Unit,
    onNewScan: () -> Unit,
    onTagDetail: () -> Unit
) {
    var selectedFilter by remember { mutableStateOf("All") }

    Scaffold(
        containerColor = Bg,
        topBar = {
            TopBar(
                title = "History",
                onBack = onBack,
                trailingIcon = Icons.Default.Search
            )
        },
        bottomBar = {
            BottomNavBar(
                active = NavDestination.HISTORY,
                onHome = onNavigateToHome,
                onWrite = onNavigateToWrite,
                onHistory = {}
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNewScan,
                containerColor = Accent,
                contentColor = AccentInk,
                shape = RoundedCornerShape(24.dp),
                icon = {
                    Icon(Icons.Default.Add, contentDescription = null)
                },
                text = {
                    Text("New scan", fontWeight = FontWeight.SemiBold)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Filter chips
            item {
                Spacer(Modifier.height(12.dp))
                FilterChipsRow(
                    selected = selectedFilter,
                    onSelect = { selectedFilter = it }
                )
                Spacer(Modifier.height(8.dp))
            }

            // Today section
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    SectionLabel(text = "Today")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                TodayItems(onTagDetail = onTagDetail)
            }

            // Earlier this week
            item {
                Spacer(Modifier.height(16.dp))
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    SectionLabel(text = "Earlier this week")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                EarlierItems(onTagDetail = onTagDetail)
            }
        }
    }
}

@Composable
private fun FilterChipsRow(selected: String, onSelect: (String) -> Unit) {
    val filters = listOf("All", "Read", "Write", "Clone", "Saved", "NDEF", "MIFARE")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            NfcChip(
                label = filter,
                active = filter == selected,
                onClick = { onSelect(filter) }
            )
        }
    }
}

@Composable
private fun TodayItems(onTagDetail: () -> Unit) {
    val items = listOf(
        HistoryItem("t1", "apartment-key", "NTAG215", "04:AB:3D:22:F1:10:80 · 2 min ago", Icons.Default.Nfc, "2m", "Read", Accent),
        HistoryItem("t2", "wifi-setup", "NTAG213", "https://home.local/wifi · 1h ago", Icons.Default.Wifi, "1h", "Write", Warn),
        HistoryItem("t3", "office-clone", "NTAG215", "Clone from apartment-key · 3h ago", Icons.Default.FileCopy, "3h", "Clone", TextMuted)
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

@Composable
private fun EarlierItems(onTagDetail: () -> Unit) {
    val items = listOf(
        HistoryItem("e1", "business-card", "MIFARE", "contact:john@example.com · Mon", Icons.Default.CreditCard, "Mon", "NDEF", Accent),
        HistoryItem("e2", "gym-locker", "NTAG213", "04:CC:1A:88:02:05:12 · Sun", Icons.Default.Lock, "Sun", "Read", Accent),
        HistoryItem("e3", "promo-tag", "NTAG216", "https://promo.shop/sale · Sat", Icons.Default.Language, "Sat", "NDEF", Warn)
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
