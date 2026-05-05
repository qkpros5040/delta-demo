package com.example.nfcall_in_one.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.components.*
import com.example.nfcall_in_one.ui.theme.*

@Composable
fun ReadScanningScreen(
    onBack: () -> Unit,
    onTagDetected: () -> Unit
) {
    Scaffold(
        containerColor = Bg,
        topBar = {
            TopBar(title = "Tap a tag", onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            // Radar animation
            RadarAnimation()

            Spacer(Modifier.height(20.dp))

            // Listening pill
            ListeningPill()

            Spacer(Modifier.height(32.dp))

            // Recent tags section
            SectionLabel(text = "Recent tags")
            Spacer(Modifier.height(12.dp))
            RecentTagsStrip()

            Spacer(Modifier.weight(1f))

            // Bottom buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    NfcBtn(
                        text = "Cancel",
                        kind = BtnKind.OUTLINE,
                        fullWidth = true,
                        onClick = onBack
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    NfcBtn(
                        text = "Manual",
                        kind = BtnKind.TONAL,
                        icon = Icons.Default.Edit,
                        fullWidth = true,
                        onClick = onTagDetected
                    )
                }
            }
        }
    }
}

@Composable
private fun RadarAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "radar")

    // 4 rings with staggered animations
    val scales = (0..3).map { index ->
        infiniteTransition.animateFloat(
            initialValue = 0.88f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1600,
                    easing = EaseInOutSine,
                    delayMillis = index * 160
                ),
                repeatMode = RepeatMode.Reverse
            ),
            label = "ring_scale_$index"
        )
    }

    val centerPulse by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "center_pulse"
    )

    val ringSizes: List<Dp> = listOf(240.dp, 190.dp, 140.dp, 96.dp)
    val ringAlphas = listOf(0.06f, 0.09f, 0.13f, 0.18f)

    Box(
        modifier = Modifier.size(260.dp),
        contentAlignment = Alignment.Center
    ) {
        ringSizes.forEachIndexed { index, size ->
            Box(
                modifier = Modifier
                    .size(size)
                    .scale(scales[index].value)
                    .clip(CircleShape)
                    .border(1.dp, Accent.copy(alpha = ringAlphas[index] + 0.04f), CircleShape)
                    .background(Accent.copy(alpha = ringAlphas[index]))
            )
        }
        // Pulsing center
        Box(
            modifier = Modifier
                .size(64.dp)
                .scale(centerPulse)
                .clip(RoundedCornerShape(18.dp))
                .background(AccentInk)
                .border(1.dp, Accent.copy(alpha = 0.5f), RoundedCornerShape(18.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.GraphicEq,
                contentDescription = null,
                tint = Accent,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
private fun ListeningPill() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceHi)
            .border(1.dp, Border, RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val pulse by rememberInfiniteTransition(label = "dot_pulse").animateFloat(
            initialValue = 0.4f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(700, easing = EaseInOutSine),
                repeatMode = RepeatMode.Reverse
            ),
            label = "dot_alpha"
        )
        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(Accent.copy(alpha = pulse))
        )
        Text(
            "Listening for NFC…",
            color = TextMuted,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

data class RecentTag(val name: String, val type: String, val icon: ImageVector)

@Composable
private fun RecentTagsStrip() {
    val tags = listOf(
        RecentTag("apartment-key", "NTAG215", Icons.Default.Nfc),
        RecentTag("wifi-home", "NTAG213", Icons.Default.Wifi),
        RecentTag("business-card", "MIFARE", Icons.Default.CreditCard),
        RecentTag("office-door", "NTAG216", Icons.Default.Lock)
    )
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(tags) { tag ->
            RecentTagChip(tag)
        }
    }
}

@Composable
private fun RecentTagChip(tag: RecentTag) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceCard)
            .border(1.dp, Border, RoundedCornerShape(14.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(SurfaceHi),
            contentAlignment = Alignment.Center
        ) {
            Icon(tag.icon, contentDescription = null, tint = Accent, modifier = Modifier.size(16.dp))
        }
        Text(tag.name, color = TextPrimary, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        Text(tag.type, color = TextDim, fontSize = 10.sp)
    }
}
