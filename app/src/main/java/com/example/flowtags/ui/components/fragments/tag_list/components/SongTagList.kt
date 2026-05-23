package com.example.flowtags.ui.components.fragments.tag_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.domain_models.dummySongTagList
import com.example.flowtags.ui.components.util.PreviewColumn

@Composable
fun SongTagList(
    modifier: Modifier = Modifier,
    songTags: List<SongTag>,
    onSongTagClick: (songTag: SongTag) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
        ,
    ) {
        items(
            items = songTags
        ) { st ->
            SongTagLi(
                songTag = st,
                onClick = onSongTagClick,
            )
        }
    }
}

@Preview
@Composable
private fun SongTagListPreview() {
    PreviewColumn {
        SongTagList(
            songTags = dummySongTagList,
            onSongTagClick = {},
        )
    }
}