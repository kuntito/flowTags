package com.example.flowtags.ui.components.fragments.tagging_songs

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.R
import com.example.flowtags.data.domain_models.Song
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.domain_models.dummySong
import com.example.flowtags.data.domain_models.dummySongTag
import com.example.flowtags.ui.components.fragments.tagging_songs.components.SongTaggingCard
import com.example.flowtags.ui.components.fragments.tagging_songs.components.YesNoPane
import com.example.flowtags.ui.components.fragments.tagging_songs.components.YesNoState
import com.example.flowtags.ui.components.util.PreviewColumn
import com.example.flowtags.ui.components.util.rememberSwipeAndFlingState
import com.example.flowtags.ui.components.util.swipeAndFling
import com.example.flowtags.ui.theme.colorFluster
import com.example.flowtags.ui.theme.colorNein
import com.example.flowtags.ui.theme.colorRaise

@Composable
fun TaggingSongsFragment(
    modifier: Modifier = Modifier,
    aaBitmap: Bitmap?,
    song: Song,
    currentTag: SongTag,
) {
    val onFlingLeft = {}
    val onFlingRight = {}
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
                albumArtBitmap = aaBitmap,
                song = song,
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

@Preview
@Composable
private fun TaggingSongsFragmentPreview() {
    val imageBitmap = BitmapFactory.decodeResource(
        LocalContext.current.resources,
        R.drawable.sample_album_art,
    )

    val song = dummySong
    val tag = dummySongTag

    PreviewColumn {
        TaggingSongsFragment(
            aaBitmap = imageBitmap,
            song = song,
            currentTag = tag
        )
    }
}