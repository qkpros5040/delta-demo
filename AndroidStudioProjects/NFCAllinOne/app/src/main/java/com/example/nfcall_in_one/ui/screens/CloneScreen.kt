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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.components.*
import com.example.nfcall_in_one.ui.theme.*

@Composable
fun CloneScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Bg,
        topBar = { TopBar(title = "Clone tag", onBack = onBack) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { Spacer(Modifier.height(4.dp)) }

            // Stepper
            item { CloneStepper() }

            // Source card
            item {
                SectionLabel(text = "Source")
                Spacer(Modifier.height(10.dp))
                SourceCard()
            }

            // Arrow connector
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.ArrowDownward,
                        contentDescription = null,
                        tint = Accent,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // Target card
            item {
                SectionLabel(text = "Target")
                Spacer(Modifier.height(10.dp))
                TargetCard()
            }

            // Compatibility checklist
            item {
                SectionLabel(text = "Compatibility")
                Spacer(Modifier.height(10.dp))
                CompatibilityChecklist()
            }

            // Buttons
            item {
                Spacer(Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        NfcBtn(text = "Cancel", kind = BtnKind.OUTLINE, fullWidth = true, onClick = onBack)
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        NfcBtn(text = "Begin clone", kind = BtnKind.PRIMARY, icon = Icons.Default.FileCopy, fullWidth = true)
                    }
                }
            }
        }
    }
}

@Composable
private fun CloneStepper() {
    val steps = listOf(
        Triple("Source", true, false),
        Triple("Verify", false, true),
        Triple("Target", false, false)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        steps.forEachIndexed { index, (label, completed, active) ->
            // Step circle
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            completed -> AccentDeep
                            active -> AccentInk
                            else -> SurfaceHi
                        }
                    )
                    .border(
                        1.dp,
                        when {
                            completed -> AccentDeep
                            active -> Accent
                            else -> Border
                        },
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (completed) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Accent, modifier = Modifier.size(16.dp))
                } else {
                    Text(
                        "${index + 1}",
                        color = if (active) Accent else TextDim,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            // Step label + connector
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 6.dp)
            ) {
                Text(
                    label,
                    color = when {
                        completed -> AccentDeep
                        active -> Accent
                        else -> TextDim
                    },
                    fontSize = 12.sp,
                    fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal
                )
            }
            if (index < steps.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
                    color = if (steps[index].second) AccentDeep else Border,
                    thickness = 1.dp
                )
            }
        }
    }
}

@Composable
private fun SourceCard() {
    CardContainer {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(AccentInk),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Nfc, contentDescription = null, tint = Accent, modifier = Modifier.size(20.dp))
                    }
                    Column {
                        Text("apartment-key", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                        Text("04:AB:3D:22:F1:10:80", color = TextDim, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                    }
                }
                Icon(Icons.Default.Check, contentDescription = null, tint = Accent, modifier = Modifier.size(18.dp))
            }
            HorizontalDivider(color = Border, thickness = 1.dp)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NfcChip(label = "NTAG215", active = false)
                NfcChip(label = "504 B", active = false)
                NfcChip(label = "NDEF", active = true)
            }
        }
    }
}

@Composable
private fun TargetCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceCard)
            .border(width = 1.dp, color = Accent.copy(alpha = 0.3f), shape = RoundedCornerShape(16.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Mini concentric rings
            Box(
                modifier = Modifier.size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                listOf(80.dp, 60.dp, 40.dp).forEachIndexed { i, size ->
                    Box(
                        modifier = Modifier
                            .size(size)
                            .clip(CircleShape)
                            .background(Accent.copy(alpha = (0.04f + i * 0.04f)))
                    )
                }
                Icon(Icons.Default.Add, contentDescription = null, tint = Accent.copy(alpha = 0.5f), modifier = Modifier.size(20.dp))
            }
            Text("Hold target tag now", color = TextMuted, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            Text("Bring a compatible NTAG215 close to your phone", color = TextDim, fontSize = 11.sp)
        }
    }
}

@Composable
private fun CompatibilityChecklist() {
    CardContainer {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            val checks = listOf(
                Pair("NTAG215 compatible", true),
                Pair("Sufficient capacity (504 B)", true),
                Pair("Writable (not locked)", true),
                Pair("Same tech type", false)
            )
            checks.forEach { (label, ok) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        if (ok) Icons.Default.CheckCircle else Icons.Default.HourglassEmpty,
                        contentDescription = null,
                        tint = if (ok) Accent else TextDim,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(label, color = if (ok) TextPrimary else TextDim, fontSize = 13.sp)
                }
            }
        }
    }
}
