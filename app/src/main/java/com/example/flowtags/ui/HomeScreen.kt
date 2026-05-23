package com.example.flowtags.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.flowtags.FlowTagsViewModel
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.ui.components.HomeScreenTopBar
import com.example.flowtags.ui.components.fragments.tag_list.TagListFragment
import com.example.flowtags.ui.components.fragments.tagging_songs.TaggingSongsFragment
import com.example.flowtags.ui.models.HomeFragmentsState

@Composable
fun HomeScreenRoot(
    flowTagsViewModel: FlowTagsViewModel,
) {
    val homeFragmentsState by flowTagsViewModel.homeFragmentsState.collectAsState()
    val onSongTagClick = flowTagsViewModel::onSongTagClick
    val onNavBack = flowTagsViewModel::onNavBack

    HomeScreen(
        homeFragmentsState = homeFragmentsState,
        onSongTagClick = onSongTagClick,
        onNavBack = onNavBack,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeFragmentsState: HomeFragmentsState,
    onSongTagClick: (songTag: SongTag) -> Unit,
    onNavBack: () -> Unit,
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
                        songTagsList = homeFragmentsState.tags,
                        onSongTagClick = onSongTagClick,
                    )
                }
                is HomeFragmentsState.TaggingSongs -> {
                    TaggingSongsFragment(
                        currentTag = homeFragmentsState.currentTag,
                    )
                }
            }
        }

    }
}
