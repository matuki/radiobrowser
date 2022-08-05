package br.com.matuki.radiobrowser.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.com.matuki.radiobrowser.MainActivity
import br.com.matuki.radiobrowser.view.PlayerScreenViewModel.*
import coil.compose.rememberAsyncImagePainter

@Composable
internal fun PlayerScreen(
    viewModel: ViewModelInterface<State, Intent, Event>,
    stationId: String,
    mediaBridge: MainActivity.MediaBridge
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.sendIntent(Intent.UpdateRadioStation(stationId))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            else -> PlayerScreenContent(
                stationId = state.radioStation?.iconUrl ?: "",
                playing = state.playing,
                onPlayPauseClick = { mediaBridge.onPlayPauseClick(state.radioStation?.id ?: "") }
            )
        }
    }
}

@Composable
private fun PlayerScreenContent(stationId: String, playing: Boolean, onPlayPauseClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(24.dp)
                .size(400.dp),
            painter = rememberAsyncImagePainter(stationId, contentScale = ContentScale.FillBounds),
            contentDescription = "Radio station icon",
        )
        IconButton(
            modifier = Modifier.size(200.dp),
            onClick = onPlayPauseClick
        ) {
            val playPauseIcon = if (playing) Icons.Filled.Pause else Icons.Filled.PlayArrow
            Icon(playPauseIcon, contentDescription = "", modifier = Modifier.size(80.dp))
        }
    }
}

