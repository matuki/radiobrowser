package br.com.matuki.radiobrowser.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.com.matuki.radiobrowser.view.PlayerScreenViewModel.*
import coil.compose.rememberAsyncImagePainter

@Composable
internal fun PlayerScreen(
    viewModel: ViewModelInterface<State, Intent, Event>,
    stationId: String
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.sendIntent(Intent.UpdateRadioStation(stationId))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            else -> PlayerScreenContent(state.radioStation?.iconUrl ?: "")
        }
    }
}

@Composable
private fun PlayerScreenContent(stationId: String) {
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
            onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.PlayArrow, contentDescription = "", modifier = Modifier.size(80.dp))
        }
    }
}

