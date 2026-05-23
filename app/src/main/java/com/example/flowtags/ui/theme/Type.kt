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
    fontWeight = FontWeight.Normal,
)

val tsBlaze = baseStyle.copy(
    fontSize = 24.sp,

)

val tsMatador = baseStyle.copy(
    fontSize = 20.sp,
)

val tsOrion = baseStyle.copy(
    fontSize = 16.sp,
)

val tsOrionMono = tsOrion
    .copy(
        fontFamily = FontFamily.Monospace,
    )

val tsHush = baseStyle.copy(
    fontSize = 12.sp,
)