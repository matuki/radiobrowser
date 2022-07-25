package br.com.matuki.radiobrowser.view

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ViewModelInterface<State, Intent, Event> {
    val state: StateFlow<State>
    val event: Flow<Event>
    fun sendIntent(intent: Intent)
}
