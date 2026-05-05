package com.example.nfcall_in_one.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.components.NfcBtn
import com.example.nfcall_in_one.ui.components.BtnKind
import com.example.nfcall_in_one.ui.theme.*

@Composable
fun OnboardingScreen(onGetStarted: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp)
                .padding(top = 60.dp, bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top spacer / brand label
            Text(
                text = "NFC All-in-One",
                color = TextDim,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )

            // Hero concentric rings
            Box(
                modifier = Modifier.size(260.dp),
                contentAlignment = Alignment.Center
            ) {
                ConcentricRings()
                // Center icon box
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Accent),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.GraphicEq,
                        contentDescription = "NFC",
                        tint = AccentInk,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            // Headline + sub
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "One tap.\nEvery NFC tag.",
                    color = TextPrimary,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 42.sp
                )
                Text(
                    text = "Read, write, clone and manage\nyour NFC tags from one place.",
                    color = TextMuted,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )
            }

            // Bottom area: dots + button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                // Dot progress (3 steps, step 1 active)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ProgressDot(active = true)
                    ProgressDot(active = false)
                    ProgressDot(active = false)
                }

                NfcBtn(
                    text = "Get started",
                    kind = BtnKind.PRIMARY,
                    icon = Icons.AutoMirrored.Filled.ArrowForward,
                    fullWidth = true,
                    onClick = onGetStarted
                )
            }
        }
    }
}

@Composable
private fun ConcentricRings() {
    val ringColors = listOf(
        Accent.copy(alpha = 0.06f),
        Accent.copy(alpha = 0.09f),
        Accent.copy(alpha = 0.12f),
        Accent.copy(alpha = 0.16f)
    )
    val sizes: List<Dp> = listOf(240.dp, 190.dp, 140.dp, 96.dp)

    sizes.forEachIndexed { index, size ->
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(ringColors[index])
        )
    }
}

@Composable
private fun ProgressDot(active: Boolean) {
    Box(
        modifier = Modifier
            .size(if (active) 24.dp else 8.dp, 8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(if (active) Accent else Border)
    )
}
