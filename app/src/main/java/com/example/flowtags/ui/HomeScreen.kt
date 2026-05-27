package com.example.flowtags.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flowtags.FlowTagsViewModel
import com.example.flowtags.data.domain_models.SongForTagging
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.ui.components.HomeScreenTopBar
import com.example.flowtags.ui.components.fragments.tag_list.TagListFragment
import com.example.flowtags.ui.components.fragments.tagging_songs.TaggingSongsFragment
import com.example.flowtags.ui.models.HomeFragmentsState
import com.example.flowtags.ui.models.SongForTagFetchState
import com.example.flowtags.ui.models.TagListState

@Composable
fun HomeScreenRoot(
    flowTagsViewModel: FlowTagsViewModel,
) {
    val homeFragmentsState by flowTagsViewModel.homeFragmentsState.collectAsState()
    val tagListState by flowTagsViewModel.tagListState.collectAsState()
    val onSongTagClick = flowTagsViewModel::onSongTagClick
    val refetchTags = flowTagsViewModel::refetchTags
    val onNavBack = flowTagsViewModel::onNavBack
    val addTagToSong = flowTagsViewModel::addTagToSong
    val addNotTagToSong = flowTagsViewModel::addNotTagToSong
//    val songForTagFetchState by flowTagsViewModel.songForTagFetchState.collectAsState()

    HomeScreen(
        homeFragmentsState = homeFragmentsState,
        tagListState = tagListState,
        onSongTagClick = onSongTagClick,
        refetchTags = refetchTags,
        onNavBack = onNavBack,
        addTagToSong = addTagToSong,
        addNotTagToSong = addNotTagToSong,
//        songForTagFetchState = songForTagFetchState,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeFragmentsState: HomeFragmentsState,
    tagListState: TagListState,
    onSongTagClick: (songTag: SongTag) -> Unit,
    refetchTags: () -> Unit,
    onNavBack: () -> Unit,
    addTagToSong: (SongForTagging, SongTag) -> Unit,
    addNotTagToSong: (SongForTagging, SongTag) -> Unit,
//    songForTagFetchState: SongForTagFetchState,
) {
    Scaffold(
        topBar = {
            HomeScreenTopBar(
                homeFragmentsState = homeFragmentsState,
                navBack = onNavBack,
            )
        },
        modifier = modifier
            .fillMaxSize()
        ,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            when(homeFragmentsState) {
                is HomeFragmentsState.TagList -> {
                    TagListFragment(
                        tagListState = tagListState,
                        onSongTagClick = onSongTagClick,
                        refetchTags = refetchTags,
                    )
                }
                is HomeFragmentsState.TaggingSongs -> {
                    val songForTagFetchState by homeFragmentsState.songForTagFetchState.collectAsState()
                    TaggingSongsFragment(
                        currentTag = homeFragmentsState.currentTag,
                        songForTagFetchState = songForTagFetchState,
                        onAddNotTagToSong = addNotTagToSong,
                        onAddTagToSong = addTagToSong,
                        navBack = onNavBack,
                    )
                }
            }
        }
    }
}
