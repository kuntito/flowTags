package com.example.flowtags.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.R
import com.example.flowtags.ui.components.util.PreviewColumn

@Composable
fun HomeScreenTopBar(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(16.dp),
    ) {
        Icon(
            painter = painterResource(R.drawable.flow_tags_logo),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}

@Preview
@Composable
private fun HomeScreenTopBarPreview() {
    PreviewColumn {
        HomeScreenTopBar()
    }
}