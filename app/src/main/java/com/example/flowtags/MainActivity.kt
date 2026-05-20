package com.example.flowtags

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.flowtags.ui.HomeScreenRoot
import com.example.flowtags.ui.components.util.ParticleLayer
import com.example.flowtags.ui.theme.FlowTagsTheme
import com.example.flowtags.ui.theme.colorDeep
import com.example.flowtags.ui.theme.colorPraise

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flowTagsViewModel: FlowTagsViewModel by viewModels {
            FlowTagsViewModelFactory()
        }

        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.dark(
                colorDeep.toArgb()
            ),
            statusBarStyle = SystemBarStyle.dark(
                colorPraise.toArgb()
            ),
        )
        setContent {
            FlowTagsTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ParticleLayer(
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(1f)
                    )
                    Box(
                        modifier = Modifier
                            .systemBarsPadding()
                            .fillMaxSize()
                    ) {
                        HomeScreenRoot(
                            flowTagsViewModel = flowTagsViewModel
                        )
                    }
                }
            }
        }
    }
}