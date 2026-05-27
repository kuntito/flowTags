package com.example.flowtags.ui.components.fragments.tagging_songs.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.data.domain_models.SongForTagging
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.ui.components.util.rememberSwipeAndFlingState
import com.example.flowtags.ui.components.util.swipeAndFling
import com.example.flowtags.ui.models.SongForTagFetchState
import com.example.flowtags.ui.theme.colorFluster
import com.example.flowtags.ui.theme.colorNein
import com.example.flowtags.ui.theme.colorRaise

@Composable
fun SongReadyForTagging(
    modifier: Modifier = Modifier,
    songForTagging: SongForTagging,
    currentTag: SongTag,
    onAddTagToSong: (SongForTagging, SongTag) -> Unit,
    onAddNotTagToSong: (SongForTagging, SongTag) -> Unit,
) {
    key(songForTagging.songId) {
        val onFlingRight = {
            onAddTagToSong(songForTagging, currentTag)
        }
        val onFlingLeft = {
            onAddNotTagToSong(songForTagging, currentTag)
        }
        val swipeAndFlingState = rememberSwipeAndFlingState(
            onFlingLeft = onFlingLeft,
            onFlingRight = onFlingRight,
        )

        val yesNoState by remember {
            derivedStateOf {
                when {
                    swipeAndFlingState.offsetX.value > 40f -> YesNoState.Yes
                    swipeAndFlingState.offsetX.value < -40f -> YesNoState.No
                    else -> YesNoState.Idle
                }
            }
        }

        val taggingCardColor by animateColorAsState(
            targetValue = when (yesNoState) {
                YesNoState.Yes -> colorFluster
                YesNoState.No -> colorNein
                YesNoState.Idle -> colorRaise
            },
            animationSpec = tween(150),
            label = "cardBgColor"
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
            ,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                ,
            ) {
                SongTaggingCard(
                    song = songForTagging,
                    cardColor = taggingCardColor,
                    isCardAtRest = swipeAndFlingState.isAtRest,
                    modifier = Modifier
                        .swipeAndFling(
                            swipeAndFlingState
                        )
                )
            }
            Box(
                modifier = Modifier.height(96.dp)
            ) {
                YesNoPane(
                    yesNoState = yesNoState,
                )
            }
        }
    }
}