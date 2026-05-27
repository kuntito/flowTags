package com.example.flowtags.ui.components.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flowtags.ui.theme.colorFluster

/**
* fills it's containers space and centers a spinner.
* */
@Composable
fun CenterSpinner(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
        ,
    ) {
        CircularProgressIndicator(
            color = colorFluster,
            strokeWidth = 2.dp
        )
    }
}