package com.example.flowtags.ui.components.fragments.tag_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.domain_models.dummySongTagList
import com.example.flowtags.ui.components.fragments.tag_list.components.SongTagList
import com.example.flowtags.ui.components.fragments.tag_list.components.TagListFetchError
import com.example.flowtags.ui.components.util.AppErrorFrame
import com.example.flowtags.ui.components.util.CenterSpinner
import com.example.flowtags.ui.components.util.CenterText
import com.example.flowtags.ui.components.util.PreviewColumn
import com.example.flowtags.ui.models.TagListState

@Composable
fun TagListFragment(
    modifier: Modifier = Modifier,
    tagListState: TagListState,
    onSongTagClick: (songTag: SongTag) -> Unit,
    refetchTags: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
        ,
    ) {
        when(tagListState) {
            TagListState.Idle -> {}
            TagListState.Fetching -> {
                CenterSpinner()
            }
            is TagListState.TagsReady -> {
                SongTagList(
                    songTags = tagListState.songTags,
                    onSongTagClick = onSongTagClick,
                )
            }
            TagListState.NoTags -> {
                // TODO might want to add create tag?
                CenterText(
                    text = "no tags."
                )
            }
            TagListState.Error -> {
                AppErrorFrame(
                    errorMessage = "couldn't fetch tags.",
                    centeredContent = {
                        TagListFetchError(
                            refetchTags = refetchTags
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun TagListFragmentPreview() {
    PreviewColumn {
        SongTagList(
            songTags = dummySongTagList,
            onSongTagClick = {},
        )
    }
}