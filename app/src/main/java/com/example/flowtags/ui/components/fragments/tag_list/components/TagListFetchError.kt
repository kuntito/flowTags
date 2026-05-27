package com.example.flowtags.ui.components.fragments.tag_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.R
import com.example.flowtags.ui.components.util.AppIconButton
import com.example.flowtags.ui.components.util.PreviewColumn
import com.example.flowtags.ui.theme.tsOrionMono

@Composable
fun TagListFetchError(
    modifier: Modifier = Modifier,
    refetchTags: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
        ,
    ) {
        AppIconButton(
            iconRes = R.drawable.ic_retry,
            onClick = refetchTags
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "refetch tags",
            style = tsOrionMono,
        )
    }
}

@Preview
@Composable
private fun TagListFetchErrorPreview() {
    PreviewColumn {
        TagListFetchError(
            refetchTags = {},
        )
    }
}