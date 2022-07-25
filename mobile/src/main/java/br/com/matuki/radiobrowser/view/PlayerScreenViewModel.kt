package br.com.matuki.radiobrowser.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.matuki.radiobrowser.shared.RadioRepository
import br.com.matuki.radiobrowser.shared.model.RadioStation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerScreenViewModel @Inject constructor(
    private val radioRepository: RadioRepository,
): ViewModel(),
    ViewModelInterface<PlayerScreenViewModel.State, PlayerScreenViewModel.Intent, PlayerScreenViewModel.Event> {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> = _state

    private val _event = MutableSharedFlow<Event>()
    override val event: Flow<Event>
        get() = _event.asSharedFlow()

    data class State(
        val radioStation: RadioStation? = null,
        val loading: Boolean = true,
        val playing: Boolean = false
    )

    sealed interface Event {
    }

    sealed interface Intent {
        data class UpdateRadioStation(val stationId: String) : Intent
    }

    override fun sendIntent(intent: Intent) = when (intent) {
        is Intent.UpdateRadioStation -> handleUpdateRadioStation(intent.stationId)
    }

    private fun handleUpdateRadioStation(newStationId: String) {
        viewModelScope.launch {
            reduce { copy(loading = true) }
            val radioStation = radioRepository.getRadioStation(newStationId)
            reduce { copy(radioStation = radioStation, loading = false) }
        }
    }

    private fun reduce(reducer: PlayerScreenViewModel.State.() -> PlayerScreenViewModel.State) {
        _state.value = _state.value.reducer()
    }
}