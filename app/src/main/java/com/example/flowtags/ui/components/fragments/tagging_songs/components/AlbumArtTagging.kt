package com.example.flowtags.ui.components.fragments.tagging_songs.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.flowtags.R
import com.example.flowtags.ui.components.util.PreviewColumn

@Composable
fun AlbumArtTagging(
    modifier: Modifier = Modifier,
    albumArtUrl: String,
    size: Float = 264f,
) {
    val shape = RoundedCornerShape(8.dp)
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(shape)
    ) {
        AsyncImage(
            model = albumArtUrl,
            contentDescription = null,
            error = painterResource(
                R.drawable.album_art_placeholder
            ),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun AlbumArtTaggingPreview() {
    val size = 320
    val albumArtUrl = "https://picsum.photos/$size/$size"

    PreviewColumn {
        AlbumArtTagging(
            albumArtUrl = albumArtUrl,
        )
    }
}