package com.example.flowtags.ui.components.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flowtags.ui.theme.colorDebit
import com.example.flowtags.ui.theme.colorFluster
import com.example.flowtags.ui.theme.colorWhite
import kotlinx.coroutines.launch

/**
 * displays a blank screen with a snack bar and the [errorMessage]
 * allows you place a composable in the center.
 * and an optional callback, after displaying the error.
* */
@Composable
fun AppErrorFrame(
    modifier: Modifier = Modifier,
    errorMessage: String,
    afterShowingErrorMessage: () -> Unit = {},
    centeredContent: @Composable () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.TopCenter, // for snack bar
        modifier = modifier
            .fillMaxSize()
        ,
    ) {
        val snackbarHostState = remember {
            SnackbarHostState()
        }

        val coroutineScope = rememberCoroutineScope()
        val displayErrorSnackBar = {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = errorMessage,
                    duration = SnackbarDuration.Short
                )
            }
        }

        LaunchedEffect(Unit) {
            displayErrorSnackBar()
            afterShowingErrorMessage()
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
            ,
        ) {
            centeredContent()
        }

        SnackbarHost(
            hostState = snackbarHostState,
            snackbar = { data ->
                AppSnackBar(
                    text = data.visuals.message,
                    bgColor = colorDebit,
                    textColor = colorWhite,
                )
            }
        )
    }
}

@Preview
@Composable
private fun AppErrorFramePreview() {
    PreviewColumn {
        AppErrorFrame(
            errorMessage = "sumn' wrong, i hold my head",
            centeredContent = {
                Text(
                    text = "vibes",
                    color = colorFluster
                )
            }
        )
    }
}