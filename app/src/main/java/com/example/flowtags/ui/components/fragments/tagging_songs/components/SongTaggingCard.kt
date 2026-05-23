package com.example.flowtags.ui.components.fragments.tagging_songs.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.R
import com.example.flowtags.data.domain_models.Song
import com.example.flowtags.data.domain_models.dummySong
import com.example.flowtags.ui.components.util.PreviewColumn
import com.example.flowtags.ui.theme.colorPlatform
import com.example.flowtags.ui.theme.colorRaise
import com.example.flowtags.ui.theme.colorWhite

@Composable
fun SongTaggingCard(
    modifier: Modifier = Modifier,
    albumArtBitmap: Bitmap?,
    song: Song,
) {
    val cardShape = RoundedCornerShape(16.dp)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(
                color = colorRaise,
                shape = cardShape,
            )
            .border(
                width = 1.5.dp,
                color = colorPlatform,
                shape = cardShape
            )
            .padding(vertical = 24.dp, horizontal = 10.dp)

            .width(304.dp)
        ,
    ) {
        AlbumArtTagging(
            albumArtBitmap = albumArtBitmap
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
    val imageBitmap = BitmapFactory.decodeResource(
        LocalContext.current.resources,
        R.drawable.sample_album_art,
    )
    val song = dummySong
    PreviewColumn {
        SongTaggingCard(
            albumArtBitmap = imageBitmap,
            song = song,
        )
    }
}