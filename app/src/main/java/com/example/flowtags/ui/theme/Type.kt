package com.example.flowtags.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val fontFamily = FontFamily.Default
val fontColor = colorFluster

val baseStyle = TextStyle(
    fontFamily = fontFamily,
    color = fontColor,
)

val tsBlaze = baseStyle.copy(
    fontSize = 24.sp,
    fontWeight = FontWeight.Normal,
)

val tsOrion = baseStyle.copy(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
)

val tsOrionMono = tsOrion
    .copy(
        fontFamily = FontFamily.Monospace,
    )
