package br.com.matuki.radiobrowser.view

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.matuki.radiobrowser.shared.RadioRepository
import br.com.matuki.radiobrowser.shared.model.RadioStation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadioListViewModel @Inject constructor(
    private val radioRepository: RadioRepository
) : ViewModel(),
    ViewModelInterface<RadioListViewModel.State, RadioListViewModel.Intent, RadioListViewModel.Event> {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> = _state

    private val _event = MutableSharedFlow<Event>()
    override val event: Flow<Event>
        get() = _event.asSharedFlow()

    data class State(
        val loading: Boolean = false,
        val playing: Boolean = false,
        val stationList: List<RadioStation> = emptyList(),
    )

    sealed interface Event {
    }

    sealed interface Intent {
    }

    override fun sendIntent(intent: Intent) {
        TODO("Not yet implemented")
    }

    init {
        viewModelScope.launch {
            handleGetRadioStations()
        }
    }

    private fun handleGetRadioStations() {
        val radioStationList = radioRepository.listRadioStations()
        reduce { copy(stationList = radioStationList) }
    }

    private fun reduce(reducer: State.() -> State) {
        _state.value = _state.value.reducer()
    }
}