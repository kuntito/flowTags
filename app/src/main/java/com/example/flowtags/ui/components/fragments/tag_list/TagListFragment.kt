package com.example.flowtags.ui.components.fragments.tag_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.domain_models.dummySongTagList
import com.example.flowtags.ui.components.fragments.tag_list.components.SongTagList
import com.example.flowtags.ui.components.util.PreviewColumn

@Composable
fun TagListFragment(
    modifier: Modifier = Modifier,
    songTagsList: List<SongTag>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
        ,
    ) {
        SongTagList(
            songTags = songTagsList,
        )
    }
}

@Preview
@Composable
private fun TagListFragmentPreview() {
    PreviewColumn {
        SongTagList(
            songTags = dummySongTagList,
        )
    }
}