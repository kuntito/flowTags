package com.example.flowtags.ui.components.fragments.tag_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.domain_models.dummySongTag
import com.example.flowtags.ui.components.util.ClickableSurface
import com.example.flowtags.ui.components.util.PreviewColumn
import com.example.flowtags.ui.theme.tsOrionMono

@Composable
fun SongTagLi(
    modifier: Modifier = Modifier,
    songTag: SongTag,
    onClick: (songTag: SongTag) -> Unit,
) {
    ClickableSurface(
        onClick = {
            onClick(songTag)
        },
        isRippleBounded = true,
        modifier = modifier
            .padding(horizontal = 8.dp)
        ,
    ) {
        Text(
            text = songTag.tagName,
            style = tsOrionMono,
            modifier = modifier
                .padding(horizontal = 8.dp)
            ,
        )
    }
}

@Preview
@Composable
private fun SongTagLiPreview() {
    PreviewColumn {
        SongTagLi(
            songTag = dummySongTag,
            onClick = {},
        )
    }
}