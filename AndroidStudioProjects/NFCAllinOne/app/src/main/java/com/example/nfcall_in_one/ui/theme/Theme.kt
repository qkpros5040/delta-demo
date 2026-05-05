package com.example.nfcall_in_one.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val NfcDarkColorScheme = darkColorScheme(
    primary = Accent,
    onPrimary = AccentInk,
    primaryContainer = AccentDeep,
    onPrimaryContainer = Accent,
    secondary = AccentDeep,
    onSecondary = Accent,
    secondaryContainer = SurfaceHi,
    onSecondaryContainer = TextPrimary,
    tertiary = Warn,
    onTertiary = AccentInk,
    background = Bg,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceCard,
    onSurfaceVariant = TextMuted,
    outline = Border,
    outlineVariant = BorderStrong,
    error = Danger,
    onError = AccentInk,
    errorContainer = Danger,
    onErrorContainer = TextPrimary,
    inverseSurface = TextPrimary,
    inverseOnSurface = Bg,
    inversePrimary = AccentDeep,
    scrim = Color(0x99000000)
)

@Composable
fun NfcTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = NfcDarkColorScheme,
        typography = Typography,
        content = content
    )
}
