package br.com.matuki.radiobrowser.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import br.com.matuki.radiobrowser.view.RadioListViewModel.State
import br.com.matuki.radiobrowser.view.RadioListViewModel.Intent
import br.com.matuki.radiobrowser.view.RadioListViewModel.Event
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.matuki.radiobrowser.shared.model.RadioStation
import coil.compose.rememberAsyncImagePainter

@Composable
internal fun RadioListScreen(
    viewModel: ViewModelInterface<State, Intent, Event>,
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            else -> RadioStationGrid(state.stationList) { stationId ->
                navController.navigate("player/$stationId")
            }
        }
    }
}

@Composable
fun RadioStationGrid(stations: List<RadioStation>, onItemClicked: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(stations) { station ->
            RadioStationItem(station, onItemClicked)
        }
    }
}

@Composable
fun RadioStationItem(station: RadioStation, onItemClicked: (String) -> Unit) {
    Box(modifier = Modifier.size(128.dp), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.clickable { onItemClicked(station.id) },
            painter = rememberAsyncImagePainter(station.iconUrl),
            contentDescription = "${station.name} radio station icon",
        )
    }
}