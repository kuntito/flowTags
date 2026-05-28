package com.example.flowtags.ui.components.fragments.tagging_songs.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.data.domain_models.dummySongTag
import com.example.flowtags.ui.components.util.PreviewColumn
import com.example.flowtags.ui.theme.colorWhite
import com.example.flowtags.ui.theme.tsBlaze
import com.example.flowtags.ui.theme.tsHush
import com.example.flowtags.ui.theme.tsMatador
import com.example.flowtags.ui.theme.tsOrion
import com.example.flowtags.ui.theme.tsOrionMono

@Composable
fun SongTitleAndArtistTagging(
    modifier: Modifier = Modifier,
    songTitle: String,
    artistStr: String,
) {
    val textColor = colorWhite
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
        ,
    ) {
        Text(
            text = songTitle,
            color = textColor,
            style = tsMatador
                .copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = artistStr,
            color = textColor,
            style = tsOrion,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun SongTitleAndArtistTaggingPreview() {
    val songTitle = "Design"
    val artistStr = "Olivetheboy"
    PreviewColumn {
        SongTitleAndArtistTagging(
            songTitle = songTitle,
            artistStr = artistStr,
        )
    }
}