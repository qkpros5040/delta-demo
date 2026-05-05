package com.example.nfcall_in_one.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.components.*
import com.example.nfcall_in_one.ui.theme.*

@Composable
fun WriteScreen(
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    var selectedType by remember { mutableStateOf("URL") }
    var urlText by remember { mutableStateOf("https://") }
    var lockTag by remember { mutableStateOf(false) }
    var androidLauncher by remember { mutableStateOf(true) }
    var compress by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Bg,
        topBar = { TopBar(title = "Write tag", onBack = onBack) },
        bottomBar = {
            BottomNavBar(
                active = NavDestination.WRITE,
                onHome = onNavigateToHome,
                onWrite = {},
                onHistory = onNavigateToHistory
            )
        }
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

            // Payload type selector
            item {
                SectionLabel(text = "Payload type")
                Spacer(Modifier.height(12.dp))
                PayloadTypeGrid(
                    selected = selectedType,
                    onSelect = { selectedType = it }
                )
            }

            // URL editor
            item {
                SectionLabel(text = "Content")
                Spacer(Modifier.height(10.dp))
                UrlEditor(value = urlText, onValueChange = { urlText = it })
            }

            // Options
            item {
                SectionLabel(text = "Options")
                Spacer(Modifier.height(8.dp))
                CardContainer {
                    ToggleRow(
                        label = "Lock tag after write",
                        sub = "Prevents future modifications",
                        icon = Icons.Default.Lock,
                        checked = lockTag,
                        onCheckedChange = { lockTag = it }
                    )
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ToggleRow(
                        label = "Android app launcher",
                        sub = "Open with NFC All-in-One",
                        icon = Icons.Default.PhoneAndroid,
                        checked = androidLauncher,
                        onCheckedChange = { androidLauncher = it }
                    )
                    HorizontalDivider(color = Border, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    ToggleRow(
                        label = "Compress payload",
                        sub = "Saves tag space",
                        icon = Icons.Default.FitScreen,
                        checked = compress,
                        onCheckedChange = { compress = it }
                    )
                }
            }

            // Capacity
            item {
                SectionLabel(text = "Capacity")
                Spacer(Modifier.height(10.dp))
                CapacityBar(used = 72, total = 504)
            }

            // CTA
            item {
                Spacer(Modifier.height(4.dp))
                NfcBtn(
                    text = "Continue to write",
                    kind = BtnKind.PRIMARY,
                    icon = Icons.AutoMirrored.Filled.ArrowForward,
                    fullWidth = true,
                    onClick = {}
                )
            }
        }
    }
}

data class PayloadTypeItem(val label: String, val icon: ImageVector)

@Composable
private fun PayloadTypeGrid(selected: String, onSelect: (String) -> Unit) {
    val types = listOf(
        PayloadTypeItem("URL", Icons.Default.Link),
        PayloadTypeItem("Text", Icons.Default.TextFields),
        PayloadTypeItem("Wi-Fi", Icons.Default.Wifi),
        PayloadTypeItem("Contact", Icons.Default.CreditCard),
        PayloadTypeItem("App", Icons.Default.PhoneAndroid),
        PayloadTypeItem("Location", Icons.Default.LocationOn)
    )
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        types.chunked(3).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                row.forEach { type ->
                    val isSelected = type.label == selected
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSelected) AccentInk else SurfaceCard)
                            .border(1.dp, if (isSelected) Accent else Border, RoundedCornerShape(14.dp))
                            .clickable { onSelect(type.label) }
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                type.icon,
                                contentDescription = type.label,
                                tint = if (isSelected) Accent else TextMuted,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                type.label,
                                color = if (isSelected) Accent else TextPrimary,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UrlEditor(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        textStyle = LocalTextStyle.current.copy(
            color = TextPrimary,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        ),
        placeholder = {
            Text("https://", color = TextDim, fontFamily = FontFamily.Monospace, fontSize = 14.sp)
        },
        leadingIcon = {
            Icon(Icons.Default.Link, contentDescription = null, tint = Accent, modifier = Modifier.size(18.dp))
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Accent,
            unfocusedBorderColor = Border,
            cursorColor = Accent,
            focusedContainerColor = SurfaceCard,
            unfocusedContainerColor = SurfaceCard,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        ),
        singleLine = true
    )
}

@Composable
private fun CapacityBar(used: Int, total: Int) {
    val fraction = used.toFloat() / total.toFloat()
    CardContainer {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("$used B used", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                Text("$total B total", color = TextMuted, fontSize = 13.sp)
            }
            LinearProgressIndicator(
                progress = { fraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Accent,
                trackColor = SurfaceHi
            )
            Text(
                "${((1f - fraction) * total).toInt()} B remaining",
                color = TextDim,
                fontSize = 11.sp
            )
        }
    }
}
