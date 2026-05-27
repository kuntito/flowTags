package com.example.flowtags.ui.components.fragments.tagging_songs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flowtags.data.domain_models.SongForTagging
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.domain_models.dummySongForTagging
import com.example.flowtags.data.domain_models.dummySongTag
import com.example.flowtags.ui.components.fragments.tagging_songs.components.SongReadyForTagging
import com.example.flowtags.ui.components.util.CenterSpinner
import com.example.flowtags.ui.components.util.CenterText
import com.example.flowtags.ui.components.util.AppErrorFrame
import com.example.flowtags.ui.components.util.PreviewColumn
import com.example.flowtags.ui.models.SongForTagFetchState
import com.example.flowtags.ui.theme.tsOrion

@Composable
fun TaggingSongsFragment(
    modifier: Modifier = Modifier,
    songForTagFetchState: SongForTagFetchState,
    currentTag: SongTag,
    onAddTagToSong: (SongForTagging, SongTag) -> Unit,
    onAddNotTagToSong: (SongForTagging, SongTag) -> Unit,
    navBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
        ,
    ) {
        when (songForTagFetchState) {
            SongForTagFetchState.Idle -> {}
            SongForTagFetchState.Fetching -> {
                CenterSpinner()
            }
            SongForTagFetchState.NoMoreSongs -> {
                CenterText(
                    text = "no more songs."
                )
            }
            is SongForTagFetchState.SongReady -> {
                SongReadyForTagging(
                    songForTagging = songForTagFetchState.song,
                    onAddTagToSong = onAddTagToSong,
                    onAddNotTagToSong = onAddNotTagToSong,
                    currentTag = currentTag,
                )
            }
            SongForTagFetchState.Error -> {
                AppErrorFrame(
                    errorMessage = "sumn' wrong.. i hold my head",
                    afterShowingErrorMessage = navBack
                )
            }
        }
    }
}

@Preview
@Composable
private fun TaggingSongsFragmentPreview() {
    val songForTagging = dummySongForTagging
    val tag = dummySongTag

    var songForTagFetchState: SongForTagFetchState by remember {
        mutableStateOf(
            SongForTagFetchState.Idle
        )
    }

    val toggleState: (SongForTagFetchState) -> Unit = { sfts ->
        when (sfts) {
            SongForTagFetchState.Idle -> {
                songForTagFetchState = SongForTagFetchState.Fetching
            }
            SongForTagFetchState.Fetching -> {
                songForTagFetchState = SongForTagFetchState.SongReady(
                    song = songForTagging
                )
            }
            is SongForTagFetchState.SongReady -> {
                songForTagFetchState = SongForTagFetchState.NoMoreSongs
            }
            SongForTagFetchState.NoMoreSongs -> {
                songForTagFetchState = SongForTagFetchState.Error
            }
            SongForTagFetchState.Error -> {
                songForTagFetchState = SongForTagFetchState.Idle
            }
        }
    }

    val navBack = {}

    PreviewColumn {
        TextButton(
            onClick = { toggleState(songForTagFetchState) }
        ) {
            Text(
                text = "toggle state",
                style = tsOrion,
            )
        }
        TaggingSongsFragment(
            songForTagFetchState = songForTagFetchState,
            currentTag = tag,
            onAddTagToSong = { _, _ -> },
            onAddNotTagToSong = { _, _ -> },
            navBack = navBack,
        )
    }
}