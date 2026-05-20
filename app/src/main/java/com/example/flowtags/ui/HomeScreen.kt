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
import com.example.flowtags.ui.components.HomeScreenTopBar
import com.example.flowtags.ui.components.fragments.tag_list.TagListFragment
import com.example.flowtags.ui.components.fragments.tagging_songs.TaggingSongsFragment
import com.example.flowtags.ui.models.HomeFragmentsState

@Composable
fun HomeScreenRoot(
    flowTagsViewModel: FlowTagsViewModel,
) {
    val homeFragmentsState by flowTagsViewModel.homeFragmentsState.collectAsState()

    HomeScreen(
        homeFragmentsState = homeFragmentsState,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeFragmentsState: HomeFragmentsState,
) {
    Scaffold(
        topBar = {
            HomeScreenTopBar()
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
                        songTagsList = homeFragmentsState.tags
                    )
                }
                HomeFragmentsState.TaggingSongs -> {
                    TaggingSongsFragment()
                }
            }
        }

    }
}
