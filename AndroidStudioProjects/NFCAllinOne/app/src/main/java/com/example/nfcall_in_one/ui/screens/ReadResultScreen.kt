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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.components.*
import com.example.nfcall_in_one.ui.theme.*

@Composable
fun ReadResultScreen(
    onBack: () -> Unit,
    onWriteToNew: () -> Unit,
    onClone: () -> Unit
) {
    Scaffold(
        containerColor = Bg,
        topBar = {
            TopBar(title = "Tag detected", onBack = onBack)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Success banner
            item {
                Spacer(Modifier.height(8.dp))
                SuccessBanner()
            }
            // Tag identity card
            item {
                SectionLabel(text = "Tag identity")
                Spacer(Modifier.height(8.dp))
                TagIdentityCard()
            }
            // Records
            item {
                SectionLabel(text = "Records")
                Spacer(Modifier.height(8.dp))
                RecordsSection()
            }
            // Raw payload
            item {
                SectionLabel(text = "Raw payload")
                Spacer(Modifier.height(8.dp))
                RawPayloadCard()
            }
            // Action buttons
            item {
                Spacer(Modifier.height(4.dp))
                ActionButtonsGrid(
                    onWriteToNew = onWriteToNew,
                    onClone = onClone
                )
            }
        }
    }
}

@Composable
private fun SuccessBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0x1A7AF0C4))
            .border(1.dp, Accent.copy(alpha = 0.25f), RoundedCornerShape(14.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Accent.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Check, contentDescription = null, tint = Accent, modifier = Modifier.size(20.dp))
        }
        Column {
            Text("Tag read successfully", color = Accent, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text("NDEF formatted · 72 bytes used", color = Accent.copy(alpha = 0.7f), fontSize = 12.sp)
        }
    }
}

@Composable
private fun TagIdentityCard() {
    CardContainer {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            TagInfoRow("Type", "NTAG215")
            HorizontalDivider(color = Border, thickness = 1.dp)
            TagInfoRow("UID", "04:AB:3D:22:F1:10:80", monospace = true)
            HorizontalDivider(color = Border, thickness = 1.dp)
            TagInfoRow("Capacity", "504 bytes (72 used)")
            HorizontalDivider(color = Border, thickness = 1.dp)
            // ATQA / SAK row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TagInfoPair("ATQA", "00 44")
                TagInfoPair("SAK", "00")
                TagInfoPair("Read-only", "No")
            }
        }
    }
}

@Composable
private fun TagInfoRow(label: String, value: String, monospace: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = TextMuted, fontSize = 13.sp)
        Text(
            value,
            color = TextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = if (monospace) FontFamily.Monospace else null
        )
    }
}

@Composable
private fun TagInfoPair(label: String, value: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(label, color = TextDim, fontSize = 11.sp)
        Spacer(Modifier.height(2.dp))
        Text(value, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily.Monospace)
    }
}

@Composable
private fun RecordsSection() {
    CardContainer {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(AccentInk),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Link, contentDescription = null, tint = Accent, modifier = Modifier.size(16.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text("URI Record", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                Text(
                    "https://example.com/nfc-tag",
                    color = Accent,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
            NfcChip(label = "NDEF", active = true)
        }
    }
}

@Composable
private fun RawPayloadCard() {
    CardContainer {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "D1 01 1B 55 03 65 78 61 6D 70 6C 65 2E 63 6F 6D\n" +
                        "2F 6E 66 63 2D 74 61 67 00 00 00 00 00 00 00 00\n" +
                        "00 00 00 00 00 00 00 00 FE 00 00 00 00 00 00 00",
                color = TextMuted,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun ActionButtonsGrid(onWriteToNew: () -> Unit, onClone: () -> Unit) {
    val actions = listOf(
        Triple("Copy URL", Icons.Default.ContentCopy, {}),
        Triple("Write to new", Icons.Default.Edit, onWriteToNew),
        Triple("Clone tag", Icons.Default.FileCopy, onClone),
        Triple("Save", Icons.Default.BookmarkAdd, {})
    )
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        actions.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                row.forEach { (label, icon, action) ->
                    Box(modifier = Modifier.weight(1f)) {
                        NfcBtn(
                            text = label,
                            kind = if (label == "Write to new") BtnKind.PRIMARY else BtnKind.TONAL,
                            icon = icon,
                            fullWidth = true,
                            onClick = action
                        )
                    }
                }
                if (row.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
