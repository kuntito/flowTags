package com.example.flowtags.ui.components.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.flowtags.ui.theme.colorFluster
import com.example.flowtags.ui.theme.tsOrionMono

/**
* fills it's containers space and centers a the text.
* */
@Composable
fun CenterText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
        ,
    ) {
        Text(
            text = text,
            style = tsOrionMono,
            color = colorFluster,
            textAlign = TextAlign.Center,
        )
    }
}