package com.example.flowtags.ui.components

import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.R
import com.example.flowtags.ui.components.util.AppIconButton
import com.example.flowtags.ui.components.util.blinkable
import com.example.flowtags.ui.models.HomeFragmentsState
import com.example.flowtags.ui.theme.colorFluster
import com.example.flowtags.ui.theme.colorWhite
import com.example.flowtags.ui.theme.tsBlaze

@Composable
fun HomeScreenTopBar(
    modifier: Modifier = Modifier,
    homeFragmentsState: HomeFragmentsState,
    navBack: () -> Unit,
) {
    val isAwayFromTagList = homeFragmentsState !is HomeFragmentsState.TagList
    BackHandler(
        enabled = isAwayFromTagList
    ) {
        navBack()
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(16.dp),
    ) {
        if (isAwayFromTagList) {
            AppIconButton(
                iconRes = R.drawable.ic_left_chevron,
                color = colorFluster,
                onClick = navBack,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        when(homeFragmentsState) {
            is HomeFragmentsState.TagList -> {
                Icon(
                    painter = painterResource(R.drawable.flow_tags_logo),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
            is HomeFragmentsState.TaggingSongs -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                    ,
                ) {
                    Text(
                        text = homeFragmentsState.currentTag.tagName,
                        style = tsBlaze
                            .copy(
                                fontWeight = FontWeight.Bold
                            ),
                        color = colorFluster,
                        modifier = Modifier
                            .blinkable()
                    )
                }
            }
        }
        if(isAwayFromTagList) {
            Box(modifier = Modifier.size(24.dp))
        }
    }
}

