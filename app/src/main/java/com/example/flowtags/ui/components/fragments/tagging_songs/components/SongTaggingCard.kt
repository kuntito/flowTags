package com.example.flowtags.ui.components.fragments.tagging_songs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.data.domain_models.SongForTagging
import com.example.flowtags.data.domain_models.dummySongForTagging
import com.example.flowtags.ui.components.util.PreviewColumn
import com.example.flowtags.ui.theme.colorPlatform
import com.example.flowtags.ui.theme.colorRaise

@Composable
fun SongTaggingCard(
    modifier: Modifier = Modifier,
    song: SongForTagging,
    cardColor: Color,
    isCardAtRest: Boolean,
) {
    val cardShape = RoundedCornerShape(16.dp)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(
                color = cardColor,
                shape = cardShape,
            )
            .then(
                if (isCardAtRest) {
                    Modifier
                        .border(
                            width = 1.5.dp,
                            color = colorPlatform,
                            shape = cardShape
                        )
                } else {
                    Modifier
                }
            )
            .padding(vertical = 24.dp, horizontal = 10.dp)
            .width(304.dp)
        ,
    ) {
        AlbumArtTagging(
            albumArtUrl = song.albumArtUrl
        )
        Spacer(modifier = Modifier.height(24.dp))
        SongTitleAndArtistTagging(
            songTitle = song.songTitle,
            artistStr = song.artistStr,
        )
    }
}

@Preview
@Composable
private fun SongTaggingCardPreview() {
    val song = dummySongForTagging
    val cardColor = colorRaise
    val isCardAtRest = true
    PreviewColumn {
        SongTaggingCard(
            song = song,
            cardColor = cardColor,
            isCardAtRest = isCardAtRest,
        )
    }
}