package com.example.flowtags.ui.components.fragments.tagging_songs.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowtags.R
import com.example.flowtags.ui.components.util.PreviewColumn

enum class YesNoState {
    Idle,
    Yes,
    No
}

@Composable
fun YesNoPane(
    modifier: Modifier = Modifier,
    yesNoState: YesNoState,
) {
    val noIcon = if (yesNoState == YesNoState.No) {
        R.drawable.ic_active_no
    } else {
        R.drawable.ic_inactive_no
    }

    val yesIcon = if (yesNoState == YesNoState.Yes) {
        R.drawable.ic_active_yes
    } else {
        R.drawable.ic_inactive_yes
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
        ,
    ) {
        Icon(
            painter = painterResource(noIcon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(64.dp)
        )
        Spacer(modifier = Modifier.width(32.dp))
        Icon(
            painter = painterResource(yesIcon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(64.dp)
        )
    }
}

@Preview
@Composable
private fun YesNoPanePreview() {
    var yesNoState by mutableStateOf(YesNoState.Idle)
    val toggleState: (yesNoState: YesNoState) -> Unit = {
        yesNoState = when(yesNoState) {
            YesNoState.Idle -> {
                YesNoState.Yes
            }
            YesNoState.Yes -> {
                YesNoState.No
            }
            YesNoState.No -> {
                YesNoState.Idle
            }
        }
    }
    PreviewColumn {
        YesNoPane(
            yesNoState = yesNoState,
            modifier = Modifier
                .clickable(
                    onClick = {
                        toggleState(yesNoState)
                    },
                )
        )
    }
}