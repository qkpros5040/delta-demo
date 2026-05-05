package com.example.nfcall_in_one.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfcall_in_one.ui.theme.*

// ─────────────────────────────────────────────────────────────
// TopBar
// ─────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBack: (() -> Unit)? = null,
    trailingIcon: ImageVector? = null,
    onTrailingClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextPrimary
                    )
                }
            }
        },
        actions = {
            if (trailingIcon != null) {
                IconButton(onClick = { onTrailingClick?.invoke() }) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = TextMuted
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Bg,
            titleContentColor = TextPrimary,
            navigationIconContentColor = TextPrimary,
            actionIconContentColor = TextMuted
        )
    )
}

// ─────────────────────────────────────────────────────────────
// BottomNavBar
// ─────────────────────────────────────────────────────────────
enum class NavDestination { HOME, WRITE, HISTORY }

@Composable
fun BottomNavBar(
    active: NavDestination,
    onHome: () -> Unit,
    onWrite: () -> Unit,
    onHistory: () -> Unit
) {
    NavigationBar(
        containerColor = Surface,
        tonalElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Border,
                shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
            )
    ) {
        NavigationBarItem(
            selected = active == NavDestination.HOME,
            onClick = onHome,
            icon = {
                Icon(
                    Icons.Default.GraphicEq,
                    contentDescription = "Home",
                    modifier = Modifier.size(22.dp)
                )
            },
            label = { Text("Home", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Accent,
                selectedTextColor = Accent,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted,
                indicatorColor = AccentInk
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Nfc,
                    contentDescription = "Read",
                    modifier = Modifier.size(22.dp)
                )
            },
            label = { Text("Read", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Accent,
                selectedTextColor = Accent,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted,
                indicatorColor = AccentInk
            )
        )
        NavigationBarItem(
            selected = active == NavDestination.WRITE,
            onClick = onWrite,
            icon = {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Write",
                    modifier = Modifier.size(22.dp)
                )
            },
            label = { Text("Write", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Accent,
                selectedTextColor = Accent,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted,
                indicatorColor = AccentInk
            )
        )
        NavigationBarItem(
            selected = active == NavDestination.HISTORY,
            onClick = onHistory,
            icon = {
                Icon(
                    Icons.Default.History,
                    contentDescription = "History",
                    modifier = Modifier.size(22.dp)
                )
            },
            label = { Text("History", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Accent,
                selectedTextColor = Accent,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted,
                indicatorColor = AccentInk
            )
        )
    }
}

// ─────────────────────────────────────────────────────────────
// NfcBtn
// ─────────────────────────────────────────────────────────────
enum class BtnKind { PRIMARY, TONAL, OUTLINE, TEXT, DANGER }

@Composable
fun NfcBtn(
    text: String,
    kind: BtnKind = BtnKind.PRIMARY,
    icon: ImageVector? = null,
    fullWidth: Boolean = false,
    onClick: () -> Unit = {}
) {
    val shape = RoundedCornerShape(24.dp)
    val modifier = if (fullWidth) Modifier.fillMaxWidth().height(48.dp) else Modifier.height(48.dp)

    when (kind) {
        BtnKind.PRIMARY -> Button(
            onClick = onClick,
            shape = shape,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent,
                contentColor = AccentInk
            )
        ) {
            if (icon != null) {
                Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        }

        BtnKind.TONAL -> FilledTonalButton(
            onClick = onClick,
            shape = shape,
            modifier = modifier,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = SurfaceHi,
                contentColor = TextPrimary
            )
        ) {
            if (icon != null) {
                Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text, fontWeight = FontWeight.Medium, fontSize = 15.sp)
        }

        BtnKind.OUTLINE -> OutlinedButton(
            onClick = onClick,
            shape = shape,
            modifier = modifier,
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderStrong),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary)
        ) {
            if (icon != null) {
                Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text, fontWeight = FontWeight.Medium, fontSize = 15.sp)
        }

        BtnKind.TEXT -> TextButton(
            onClick = onClick,
            modifier = modifier,
            colors = ButtonDefaults.textButtonColors(contentColor = Accent)
        ) {
            if (icon != null) {
                Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text, fontWeight = FontWeight.Medium, fontSize = 15.sp)
        }

        BtnKind.DANGER -> Button(
            onClick = onClick,
            shape = shape,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0x33FF8A7A),
                contentColor = Danger
            )
        ) {
            if (icon != null) {
                Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(text, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        }
    }
}

// ─────────────────────────────────────────────────────────────
// SectionLabel
// ─────────────────────────────────────────────────────────────
@Composable
fun SectionLabel(
    text: String,
    action: String? = null,
    onAction: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text.uppercase(),
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp,
            color = TextDim
        )
        if (action != null) {
            Text(
                text = action,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Accent,
                modifier = Modifier.clickable { onAction?.invoke() }
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────
// NfcChip
// ─────────────────────────────────────────────────────────────
@Composable
fun NfcChip(
    label: String,
    active: Boolean = false,
    icon: ImageVector? = null,
    onClick: () -> Unit = {}
) {
    val bgColor = if (active) AccentInk else SurfaceHi
    val borderColor = if (active) Accent else Border
    val textColor = if (active) Accent else TextMuted

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, tint = textColor, modifier = Modifier.size(14.dp))
        }
        Text(
            text = label,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

// ─────────────────────────────────────────────────────────────
// ToggleRow
// ─────────────────────────────────────────────────────────────
@Composable
fun ToggleRow(
    label: String,
    sub: String? = null,
    icon: ImageVector? = null,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
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
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(label, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            if (sub != null) {
                Text(sub, color = TextDim, fontSize = 12.sp)
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AccentInk,
                checkedTrackColor = Accent,
                uncheckedThumbColor = TextDim,
                uncheckedTrackColor = SurfaceHi,
                uncheckedBorderColor = Border
            )
        )
    }
}

// ─────────────────────────────────────────────────────────────
// HistoryRow
// ─────────────────────────────────────────────────────────────
data class HistoryItem(
    val id: String,
    val name: String,
    val type: String,
    val subLabel: String,
    val icon: ImageVector,
    val time: String,
    val badge: String,
    val badgeColor: Color
)

@Composable
fun HistoryRow(item: HistoryItem, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceHi),
            contentAlignment = Alignment.Center
        ) {
            Icon(item.icon, contentDescription = null, tint = Accent, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                item.name,
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                item.subLabel,
                color = TextDim,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(Modifier.width(8.dp))
        Column(horizontalAlignment = Alignment.End) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(item.badgeColor.copy(alpha = 0.15f))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(item.badge, color = item.badgeColor, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(4.dp))
            Text(item.time, color = TextDim, fontSize = 11.sp)
        }
    }
}

// ─────────────────────────────────────────────────────────────
// Misc small helpers
// ─────────────────────────────────────────────────────────────

@Composable
fun CardContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceCard)
            .border(1.dp, Border, RoundedCornerShape(16.dp))
    ) {
        content()
    }
}
